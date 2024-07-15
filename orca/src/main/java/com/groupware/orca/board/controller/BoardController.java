package com.groupware.orca.board.controller;

import com.groupware.orca.board.service.BoardFileService;
import com.groupware.orca.board.service.BoardService;
import com.groupware.orca.board.service.BookmarkService;
import com.groupware.orca.board.service.CommentService;
import com.groupware.orca.board.vo.BoardFileVo;
import com.groupware.orca.board.vo.BoardVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("orca/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;
    private final CommentService commentService;
    private final BookmarkService bookmarkService;

    @GetMapping
    public String getBoard() {
        return "board/board";
    }

    @GetMapping("/insert")
    public String insertDisplay() {
        return "board/insert";
    }

    @GetMapping("/list/{categoryNo}")
    public @ResponseBody Map<String, Object> getBoardList(
            @PathVariable("categoryNo") int categoryNo,
            @RequestParam("page") int page,
            @RequestParam("rows") int rows) {
        List<BoardVo> boardList = boardService.getBoardList(categoryNo, page, rows);
        int totalRecords = boardService.getBoardCount(categoryNo);
        int totalPages = (int) Math.ceil((double) totalRecords / rows);

        Map<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("total", totalPages);
        result.put("records", totalRecords);
        result.put("rows", boardList);

        return result;
    }

    @GetMapping("/{boardNo}")
    public @ResponseBody BoardVo getBoardDetail(@PathVariable("boardNo") int boardNo) {
        BoardVo boardDetail = boardService.getBoardDetail(boardNo);
        boardService.hit(boardNo);
        return boardDetail;
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchBoardByTitle(
            @RequestParam("title") String title,
            @RequestParam("categoryNo") int categoryNo,
            @RequestParam("page") int page,
            @RequestParam("rows") int rows) {
        List<BoardVo> boardList = boardService.searchBoard(title, categoryNo, page, rows);
        int totalRecords = boardService.getSearchCount(title, categoryNo);
        int totalPages = (int) Math.ceil((double) totalRecords / rows);

        Map<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("total", totalPages);
        result.put("records", totalRecords);
        result.put("rows", boardList);

        if (boardList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute BoardVo vo, Model model, HttpSession httpSession) {
        UserVo loginUserVo = (UserVo) httpSession.getAttribute("loginUserVo");
        if (loginUserVo != null) {
            int insertUserNo = loginUserVo.getEmpNo();
            vo.setInsertUserNo(insertUserNo);
        }

        if (vo.getCategoryNo() == 0) {
            model.addAttribute("message", "유효하지 않은 카테고리 번호입니다.");
            return "board/insert";
        }

        if (vo.getCategoryNo() == 3) {
            vo.setIsAnonymous("Y");
        } else {
            vo.setIsAnonymous("N");
        }

        boardService.boardInsert(vo);

        @SuppressWarnings("unchecked")
        List<BoardFileVo> fileList = (List<BoardFileVo>) httpSession.getAttribute("uploadedFiles");
        if (fileList != null) {
            for (BoardFileVo fileVo : fileList) {
                fileVo.setBoardNo(vo.getBoardNo());
                boardFileService.insertFile(fileVo);
            }
            httpSession.removeAttribute("uploadedFiles");
        }

        return "redirect:/orca/board";
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(HttpSession httpSession, @RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {
        Map<String, Object> response = new HashMap<>();
        if (!file.isEmpty()) {
            String originFileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            ServletContext context = req.getServletContext();
            String path = context.getRealPath("/static/upload/");

            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String random = UUID.randomUUID().toString();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String changeName = System.currentTimeMillis() + "_" + random + ext;
            FileOutputStream fos = new FileOutputStream(path + "/" + changeName);
            byte[] buf = new byte[1024];
            int size;
            while ((size = is.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }

            is.close();
            fos.close();

            BoardFileVo fileVo = new BoardFileVo();
            fileVo.setFileOriginName(originFileName);
            fileVo.setFileSaveName(changeName);
            fileVo.setFileDelYn("N");

            @SuppressWarnings("unchecked")
            List<BoardFileVo> uploadedFiles = (List<BoardFileVo>) httpSession.getAttribute("uploadedFiles");
            if (uploadedFiles == null) {
                uploadedFiles = new ArrayList<>();
            }
            uploadedFiles.add(fileVo);
            httpSession.setAttribute("uploadedFiles", uploadedFiles);

            response.put("link", "/static/upload/" + changeName);
            response.put("message", "파일 업로드 성공.");
        } else {
            response.put("message", "업로드된 파일이 없습니다.");
        }

        return response;
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardVo boardVo, HttpSession httpSession) {
        UserVo loginUserVo = (UserVo) httpSession.getAttribute("loginUserVo");
        if (loginUserVo != null) {
            int insertUserNo = loginUserVo.getEmpNo();
            boardVo.setInsertUserNo(insertUserNo);
        }
        boardService.boardUpdate(boardVo);
        return "redirect:/orca/board";
    }

    @DeleteMapping("/{boardNo}")
    public @ResponseBody String deleteBoard(@PathVariable("boardNo") int boardNo, HttpSession session) {
        UserVo loginUserVo = (UserVo) session.getAttribute("loginUserVo");
        if (loginUserVo != null) {
            int empNo = loginUserVo.getEmpNo();
            // 자식 테이블 데이터 먼저 삭제
            boardService.deleteLikesByBoardNo(boardNo);  // 좋아요 데이터 삭제
            commentService.deleteCommentsByBoardNo(boardNo);
            bookmarkService.deleteBookmarkByBoardNoAndEmpNo(boardNo, empNo);
            boardFileService.deleteFile(boardNo);
            boardService.deletePenaltyByBoardNo(boardNo);
            // 부모 테이블 데이터 삭제
            boardService.boardDelete(boardNo);
        }
        return "게시물이 삭제되었습니다.";
    }

    @GetMapping("/updatePage")
    public String updatePage(@RequestParam("boardNo") int boardNo, Model model) {
        BoardVo boardVo = boardService.getBoardDetail(boardNo);
        model.addAttribute("board", boardVo);
        return "board/updatePage";
    }

    @GetMapping("/statistics")
    public String getStatisticsPage() {
        return "board/statistics";
    }

    @GetMapping("/statsByDate")
    @ResponseBody
    public List<Map<String, Object>> getStatsByDate() {
        return boardService.getStatsByDate();
    }

    @PostMapping("/hit/{boardNo}")
    public ResponseEntity<String> hit(@PathVariable("boardNo") int boardNo) {
        boardService.hit(boardNo);
        return ResponseEntity.ok("조회수 증가 성공");
    }

    @PostMapping("/like/{boardNo}")
    public ResponseEntity<String> likePost(@PathVariable("boardNo") int boardNo, HttpSession session) {
        UserVo loginUser = (UserVo) session.getAttribute("loginUserVo");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        int empNo = loginUser.getEmpNo();
        int result = boardService.addLike(boardNo, empNo);
        if (result > 0) {
            return ResponseEntity.ok("좋아요를 눌렀습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("좋아요 실패");
        }
    }

    @DeleteMapping("/like/{boardNo}")
    public ResponseEntity<String> unlikePost(@PathVariable("boardNo") int boardNo, HttpSession session) {
        UserVo loginUser = (UserVo) session.getAttribute("loginUserVo");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        int empNo = loginUser.getEmpNo();
        int result = boardService.removeLike(boardNo, empNo);
        if (result > 0) {
            return ResponseEntity.ok("좋아요를 취소했습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("좋아요 취소 실패");
        }
    }

    @GetMapping("/like/{boardNo}")
    public ResponseEntity<Boolean> checkLike(@PathVariable("boardNo") int boardNo, HttpSession session) {
        UserVo loginUser = (UserVo) session.getAttribute("loginUserVo");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        int empNo = loginUser.getEmpNo();
        boolean isLiked = boardService.isLiked(boardNo, empNo);
        return ResponseEntity.ok(isLiked);
    }

    @GetMapping("/likes/count/{boardNo}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable("boardNo") int boardNo) {
        int likeCount = boardService.getLikeCount(boardNo);
        return ResponseEntity.ok(likeCount);
    }

    @PostMapping("/penalty")
    public ResponseEntity<String> reportBoard(@RequestParam("penaltyCategoryNo") int categoryNo,
                                              @RequestParam("penaltyContent") String content,
                                              @RequestParam("boardNo") int boardNo,
                                              HttpSession session) {
        UserVo loginUserVo = (UserVo) session.getAttribute("loginUserVo");
        if (loginUserVo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        int empNo = loginUserVo.getEmpNo();
        boardService.reportBoard(boardNo, categoryNo, content, empNo);

        boolean isHidden = boardService.checkAndHideBoard(boardNo);
        if (isHidden) {
            return ResponseEntity.ok("신고가 누적되어 게시물이 숨겨졌습니다.");
        } else {
            return ResponseEntity.ok("신고가 접수되었습니다.");
        }
    }



    @GetMapping("/penalty/categories")
    public ResponseEntity<List<Map<String, Object>>> getPenaltyCategories() {
        List<Map<String, Object>> categories = boardService.getPenaltyCategories();
        return ResponseEntity.ok(categories);
    }
}