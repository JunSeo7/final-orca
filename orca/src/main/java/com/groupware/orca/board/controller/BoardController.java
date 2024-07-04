package com.groupware.orca.board.controller;

import com.groupware.orca.board.mapper.BoardFileMapper;
import com.groupware.orca.board.service.BoardFileService;
import com.groupware.orca.board.service.BoardService;
import com.groupware.orca.board.service.CommentService;
import com.groupware.orca.board.vo.BoardFileVo;
import com.groupware.orca.board.vo.BoardVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;
    private final CommentService commentService;

    @GetMapping
    public String getBoard() {
        return "board/board";
    }

    @GetMapping("/insert")
    public String insertDisplay() {
        return "board/insert";
    }

    @GetMapping("/list/{categoryNo}")
    public @ResponseBody List<BoardVo> getBoardList(@PathVariable("categoryNo") int categoryNo) {
        return boardService.getBoardList(categoryNo);
    }

    @GetMapping("/{boardNo}")
    public @ResponseBody BoardVo getBoardDetail(@PathVariable("boardNo") int boardNo) {
        BoardVo boardDetail = boardService.getBoardDetail(boardNo);
        boardService.hit(boardNo);
        return boardDetail;
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardVo>> searchBoardByTitle(@RequestParam("title") String title, @RequestParam("categoryNo") int categoryNo) {
        List<BoardVo> boardList = boardService.searchBoard(title, categoryNo);
        if (boardList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boardList);
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute BoardVo vo, Model model, HttpSession httpSession) {
        String insertUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setInsertUserNo(Integer.parseInt(insertUserNo));

        // CATEGORY_NO 값이 유효한지 확인
        if (vo.getCategoryNo() == 0) {
            model.addAttribute("message", "Invalid category number.");
            return "board/insert";
        }

        if (vo.getCategoryNo() == 3) {
            vo.setIsAnonymous("Y");
        } else {
            vo.setIsAnonymous("N");
        }

        // 게시글 삽입
        boardService.boardInsert(vo);

        // 세션에서 파일 정보 가져오기
        List<BoardFileVo> fileList = (List<BoardFileVo>) httpSession.getAttribute("uploadedFiles");
        if (fileList != null) {
            for (BoardFileVo fileVo : fileList) {
                fileVo.setBoardNo(vo.getBoardNo()); // 생성된 게시글 번호 설정
                boardFileService.insertFile(fileVo);
            }
            // 파일 정보 세션에서 제거
            httpSession.removeAttribute("uploadedFiles");
        }

        return "redirect:/board";
    }
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(HttpSession httpSession, @RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {
        Map<String, Object> response = new HashMap<>();
        // 파일이 업로드된 경우
        if (!file.isEmpty()) {
            // 파일을 서버에 저장하기
            String originFileName = file.getOriginalFilename(); // 원본 파일 이름을 가져옴
            InputStream is = file.getInputStream(); // 파일의 입력 스트림을 가져옴

            ServletContext context = req.getServletContext();
            String path = context.getRealPath("/static/upload/");

            File dir = new File(path); // 파일 저장 경로의 디렉토리 객체 생성
            if (!dir.exists()) {
                dir.mkdirs(); // 디렉토리가 존재하지 않으면 생성
            }

            String random = UUID.randomUUID().toString(); // 고유한 파일 이름 생성을 위한 랜덤 문자열 생성
            String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 파일 확장자를 가져옴
            String changeName = System.currentTimeMillis() + "_" + random + ext; // 현재 시간과 랜덤 문자열을 조합하여 고유한 파일 이름 생성
            FileOutputStream fos = new FileOutputStream(path + "/" + changeName); // 파일 저장을 위한 출력 스트림 생성
            byte[] buf = new byte[1024]; // 파일을 읽고 쓰기 위한 버퍼 생성
            int size = 0;
            while ((size = is.read(buf)) != -1) { // 입력 스트림에서 데이터를 읽어 버퍼에 저장
                fos.write(buf, 0, size); // 버퍼에 있는 데이터를 출력 스트림에 씀
            }

            is.close(); // 입력 스트림 닫기
            fos.close(); // 출력 스트림 닫기

            // DB에 파일 정보 저장 (세션에 저장)
            BoardFileVo fileVo = new BoardFileVo();
            fileVo.setFileOriginName(originFileName);
            fileVo.setFileSaveName(changeName);
            fileVo.setFileDelYn("N");

            // 세션에 업로드된 파일 정보 저장
            List<BoardFileVo> uploadedFiles = (List<BoardFileVo>) httpSession.getAttribute("uploadedFiles");
            if (uploadedFiles == null) {
                uploadedFiles = new ArrayList<>();
            }
            uploadedFiles.add(fileVo);
            httpSession.setAttribute("uploadedFiles", uploadedFiles);

            // 파일 접근 URL 반환
            response.put("link", "/static/upload/" + changeName);

            response.put("message", "File uploaded successfully.");
        } else {
            response.put("message", "No file uploaded.");
        }

        return response;
    }


    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardVo boardVo, HttpSession httpSession) {
        String insertUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        boardVo.setInsertUserNo(Integer.parseInt(insertUserNo));
        boardService.boardUpdate(boardVo);
        return "redirect:/board";
    }

    @DeleteMapping("/{boardNo}")
    public @ResponseBody String deleteBoard(@PathVariable("boardNo") int boardNo) {
        // 댓글 먼저 삭제
        commentService.deleteCommentsByBoardNo(boardNo);
        //사진 삭제
       boardFileService.deleteFile(boardNo);
        // 게시글 삭제
        boardService.boardDelete(boardNo);
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

    // 조회수 증가
    @PostMapping("/hit/{boardNo}")
    public ResponseEntity<String> hit(@PathVariable("boardNo") int boardNo) {
        boardService.hit(boardNo);
        return ResponseEntity.ok("조회수 증가 성공");
    }

}
