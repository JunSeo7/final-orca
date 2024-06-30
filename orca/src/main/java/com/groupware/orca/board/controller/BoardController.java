package com.groupware.orca.board.controller;

import com.groupware.orca.board.service.BoardFileService;
import com.groupware.orca.board.service.BoardService;
import com.groupware.orca.board.service.CommentService;
import com.groupware.orca.board.vo.BoardFileVo;
import com.groupware.orca.board.vo.BoardVo;
import com.groupware.orca.user.vo.UserVo;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;
    private final CommentService commentService;

    @GetMapping("/board")
    public String getBoard() {
        return "board/board";
    }

    @GetMapping("/board/insert")
    public String insertDisplay() {
        return "board/insert";
    }

    @GetMapping("/board/list/{categoryNo}")
    public @ResponseBody List<BoardVo> getBoardList(@PathVariable("categoryNo") int categoryNo) {
        return boardService.getBoardList(categoryNo);
    }

    @GetMapping("/board/{boardNo}")
    public @ResponseBody BoardVo getBoardDetail(@PathVariable("boardNo") int boardNo) {
        BoardVo boardDetail = boardService.getBoardDetail(boardNo);
        boardService.hit(boardNo);
        return boardDetail;
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardVo>> searchBoardByTitle(@RequestParam("title") String title, @RequestParam("categoryNo") int categoryNo) {
        try {
            List<BoardVo> boardList = boardService.searchBoard(title, categoryNo);
            if (boardList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(boardList);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/board/insert")
    public String insert(@ModelAttribute BoardVo vo, Model model, HttpSession httpSession) {
        String insertUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setInsertUserNo(Integer.parseInt(insertUserNo));
        boardService.boardInsert(vo);
        return "redirect:/board";
    }

    @PostMapping("/board/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String uploadDir = "C:/dev/setup/uploads/";
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir, fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);

            // DB에 파일 정보 저장
            BoardFileVo fileVo = new BoardFileVo();
            fileVo.setBoardNo(null); // null로 설정
            fileVo.setFileOriginName(fileName);
            fileVo.setFileSaveName(fileName);
            fileVo.setFileDelYn("N");
            boardFileService.insertFile(fileVo);

            response.put("link", "/uploads/" + fileName);
        } catch (IOException e) {
            log.error("이미지 업로드 실패", e);
            response.put("error", "이미지 업로드 실패");
        }
        return response;
    }

    @PostMapping("/board/update")
    public String updateBoard(@ModelAttribute BoardVo boardVo, HttpSession httpSession) {
        String insertUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        boardVo.setInsertUserNo(Integer.parseInt(insertUserNo));
        boardService.boardUpdate(boardVo);
        return "redirect:/board";
    }

    @DeleteMapping("/board/{boardNo}")
    public @ResponseBody String deleteBoard(@PathVariable("boardNo") int boardNo) {
        try {
            // 댓글 먼저 삭제
            commentService.deleteCommentsByBoardNo(boardNo);
            // 게시글 삭제
            boardService.boardDelete(boardNo);
            return "게시물이 삭제되었습니다.";
        } catch (Exception e) {
            e.printStackTrace(); // 오류 로그 출력
            return "게시물 삭제에 실패했습니다.";
        }
    }

    @GetMapping("/board/updatePage")
    public String updatePage(@RequestParam("boardNo") int boardNo, Model model) {
        BoardVo boardVo = boardService.getBoardDetail(boardNo);
        model.addAttribute("board", boardVo);
        return "board/updatePage";
    }

    @GetMapping("/board/statistics")
    public String getStatisticsPage() {
        return "board/statistics";
    }

    @GetMapping("/board/statsByDate")
    @ResponseBody
    public List<Map<String, Object>> getStatsByDate() {
        return boardService.getStatsByDate();
    }
}
