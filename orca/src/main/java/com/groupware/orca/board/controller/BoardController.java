package com.groupware.orca.board.controller;

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

        if (vo.getCategoryNo() == 0) {
            model.addAttribute("message", "Invalid category number.");
            return "board/insert";
        }

        if (vo.getCategoryNo() == 3) {
            vo.setIsAnonymous("Y");
        } else {
            vo.setIsAnonymous("N");
        }

        boardService.boardInsert(vo);

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

            List<BoardFileVo> uploadedFiles = (List<BoardFileVo>) httpSession.getAttribute("uploadedFiles");
            if (uploadedFiles == null) {
                uploadedFiles = new ArrayList<>();
            }
            uploadedFiles.add(fileVo);
            httpSession.setAttribute("uploadedFiles", uploadedFiles);

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
        return "redirect:/orca/board";
    }

    @DeleteMapping("/{boardNo}")
    public @ResponseBody String deleteBoard(@PathVariable("boardNo") int boardNo) {
        commentService.deleteCommentsByBoardNo(boardNo);
        boardFileService.deleteFile(boardNo);
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

    @PostMapping("/hit/{boardNo}")
    public ResponseEntity<String> hit(@PathVariable("boardNo") int boardNo) {
        boardService.hit(boardNo);
        return ResponseEntity.ok("조회수 증가 성공");
    }
}
