package com.groupware.orca.board.controller;

import com.groupware.orca.board.service.BookmarkService;
import com.groupware.orca.board.vo.BookmarkVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/list")
    public @ResponseBody List<BookmarkVo> getBookmarkList(HttpSession session) {
        int empNo = ((UserVo) session.getAttribute("loginUserVo")).getEmpNo();
        return bookmarkService.getBookmarksByEmpNo(empNo);
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> addBookmark(@RequestBody BookmarkVo bookmarkVo, HttpSession session) {
        int empNo = ((UserVo) session.getAttribute("loginUserVo")).getEmpNo();
        bookmarkVo.setEmpNo(empNo);
        bookmarkService.insertBookmark(bookmarkVo);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @DeleteMapping("/deleteByBoardNo/{boardNo}")
    public @ResponseBody ResponseEntity<String> deleteBookmarkByBoardNo(@PathVariable("boardNo") int boardNo, HttpSession session) {
        int empNo = ((UserVo) session.getAttribute("loginUserVo")).getEmpNo();
        bookmarkService.deleteBookmarkByBoardNoAndEmpNo(boardNo, empNo);
        return ResponseEntity.ok("북마크가 삭제되었습니다.");
    }
}
