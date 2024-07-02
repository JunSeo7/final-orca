package com.groupware.orca.board.controller;

import com.groupware.orca.board.service.CommentService;
import com.groupware.orca.board.vo.CommentVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public @ResponseBody CommentVo addComment(@RequestBody CommentVo commentVo, HttpSession httpSession) {
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        commentVo.setInsertUserNo(Integer.parseInt(userVo.getEmpNo()));

        int categoryNo = commentService.getCategoryNoByBoardNo(commentVo.getBoardNo());
        if (categoryNo == 3) { // 익명 게시판일 경우
            commentVo.setIsAnonymous('Y');
        } else {
            commentVo.setIsAnonymous('N');
        }

        commentService.insertComment(commentVo);
        return commentVo;
    }

    @PostMapping("/edit")
    public @ResponseBody CommentVo editComment(@RequestBody CommentVo commentVo) {
        commentService.updateComment(commentVo);
        return commentVo;
    }

    @PostMapping("/delete")
    public @ResponseBody String deleteComment(@RequestParam("boardChatNo") int boardChatNo) {
        commentService.deleteComment(boardChatNo);
        return "댓글이 삭제되었습니다.";
    }

    @GetMapping("/list")
    public @ResponseBody List<CommentVo> getComments(@RequestParam("boardNo") int boardNo) {
        return commentService.getCommentsByBoardNo(boardNo);
    }
}
