package com.groupware.orca.board.controller;

import com.groupware.orca.board.service.BoardFileService;
import com.groupware.orca.board.service.BoardService;
import com.groupware.orca.board.service.CommentService;
import com.groupware.orca.board.vo.BoardFileVo;
import com.groupware.orca.board.vo.BoardVo;
import com.groupware.orca.board.vo.CommentVo;
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
    private final CommentService commentService;
    private final BoardFileService boardFileService;

    // 게시판 화면
    @GetMapping("/board")
    public String getBoard() {
        return "board/board";
    }

    // 게시판 작성 화면
    @GetMapping("/board/insert")
    public String insertDisplay() {
        return "board/insert";
    }

    // 게시물 리스트 보기
    @GetMapping("/board/list/{categoryNo}")
    public @ResponseBody List<BoardVo> getBoardList(@PathVariable("categoryNo") int categoryNo) {
        return boardService.getBoardList(categoryNo);
    }

    @GetMapping("/board/{boardNo}")
    public @ResponseBody BoardVo getBoardDetail(@PathVariable("boardNo") int boardNo) {
        BoardVo boardDetail = boardService.getBoardDetail(boardNo);
        System.out.println("Map Latitude: " + boardDetail.getLatitude());
        System.out.println("Map Longitude: " + boardDetail.getLongitude());
        boardService.hit(boardNo);
        return boardDetail;
    }

    // 게시물 수정 페이지 이동
    @GetMapping("/board/updatePage")
    public String updatePage(@RequestParam("boardNo") int boardNo, Model model) {
        BoardVo boardVo = boardService.getBoardDetail(boardNo);
        model.addAttribute("board", boardVo);
        return "board/updatePage";
    }

    // 게시물 수정
    @PostMapping("/board/update")
    public String updateBoard(@ModelAttribute BoardVo boardVo) {
        boardService.boardUpdate(boardVo);
        return "redirect:/board";
    }

    // 게시물 삭제
    @PostMapping("/board/delete/{boardNo}")
    public @ResponseBody String delete(@PathVariable("boardNo") int boardNo) {
        boardService.boardDelete(boardNo);
        return "게시물이 삭제되었습니다.";
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardVo>> searchBoardByTitle(@RequestParam("title") String title, @RequestParam("categoryNo") String categoryNoStr) {
        try {
            int categoryNo = Integer.parseInt(categoryNoStr); // 문자열을 정수로 변환
            List<BoardVo> boardList = boardService.searchBoard(title, categoryNo);
            if (boardList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(boardList);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 잘못된 숫자 형식 처리
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 게시판 작성 내용
    @PostMapping("/board/insert")
    public String insert(@ModelAttribute BoardVo vo, Model model, HttpSession httpSession) {
        String insertUserNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setInsertUserNo(Integer.parseInt(insertUserNo));
        boardService.boardInsert(vo);
        return "redirect:/board";
    }

    @PostMapping("/board/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("boardNo") int boardNo) {
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
            fileVo.setBoardNo(boardNo);
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



    //조회수 증가
    @PostMapping("/board/hit/{boardNo}")
    public ResponseEntity<String> hit(@PathVariable("boardNo") int boardNo) {
        try {
            boardService.hit(boardNo);
            return ResponseEntity.ok("조회수 증가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패");
        }
    }

    @PostMapping("/board/comment")
    public String addComment(@ModelAttribute CommentVo commentVo) {
        commentService.insertComment(commentVo);
        return "redirect:/board";
    }

    @PostMapping("/board/comment/edit")
    public @ResponseBody CommentVo editComment(@ModelAttribute CommentVo commentVo) {
        commentService.updateComment(commentVo);
        return commentVo;
    }

    @PostMapping("/board/comment/reply")
    public @ResponseBody CommentVo addReply(@ModelAttribute CommentVo commentVo) {
        commentService.insertComment(commentVo);
        return commentVo;
    }

    @PostMapping("/board/comment/delete")
    public @ResponseBody String deleteComment(@RequestParam("commentNo") String commentNo) {
        commentService.deleteComment(commentNo);
        return "댓글이 삭제되었습니다.";
    }
}
