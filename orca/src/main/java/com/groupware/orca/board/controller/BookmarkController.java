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

    @GetMapping
    public String getBookmarkPage() {
        return "bookmark/bookmark";
    }

    @GetMapping("/list")
    public @ResponseBody List<BookmarkVo> getBookmarkList(HttpSession session) {
        String empNo = ((UserVo) session.getAttribute("loginUserVo")).getEmpNo();
        return bookmarkService.getBookmarksByEmpNo(Integer.parseInt(empNo));
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> addBookmark(@RequestBody BookmarkVo bookmarkVo, HttpSession session) {
        String empNo = ((UserVo) session.getAttribute("loginUserVo")).getEmpNo();
        bookmarkVo.setEmpNo(Integer.parseInt(empNo));
        System.out.println("보드 = " + bookmarkVo);
        bookmarkService.insertBookmark(bookmarkVo);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @DeleteMapping("/deleteByBoardNo/{boardNo}")
    public @ResponseBody ResponseEntity<String> deleteBookmarkByBoardNo(@PathVariable("boardNo") int boardNo, HttpSession session) {
        String empNo = ((UserVo) session.getAttribute("loginUserVo")).getEmpNo();
        bookmarkService.deleteBookmarkByBoardNoAndEmpNo(boardNo, Integer.parseInt(empNo));
        return ResponseEntity.ok("북마크가 삭제되었습니다.");
    }
}
