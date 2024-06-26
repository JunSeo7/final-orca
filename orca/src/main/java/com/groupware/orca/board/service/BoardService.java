package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.BoardDao;
import com.groupware.orca.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao dao;

    public BoardVo getBoardDetail(String boardNo) {
        return dao.getBoardDetail(boardNo);
    }

    public int boardInsert(BoardVo vo) {
        return dao.boardInsert(vo);
    }

    public int boardUpdate(BoardVo vo) {
        return dao.boardUpdate(vo);
    }

    public int boardDelete(String boardNo) {
        return dao.boardDelete(boardNo);
    }

    public List<BoardVo> searchBoard(String keyword) {
        return dao.searchBoard(keyword);
    }

    public void hit(String boardNo) {
        dao.hit(boardNo);
    }

    public List<BoardVo> getBoardList(String categoryNo) {
        return dao.getBoardList(categoryNo);
    }
}
