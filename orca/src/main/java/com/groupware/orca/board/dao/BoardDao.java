package com.groupware.orca.board.dao;


import com.groupware.orca.board.mapper.BoardMapper;
import com.groupware.orca.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private BoardMapper mapper;

    public BoardVo getBoardDetail(String boardNo) {
        return mapper.getBoardDetail(boardNo);
    }

    public int boardInsert(BoardVo vo) {
        return mapper.boardInsert(vo);
    }

    public int boardUpdate(BoardVo vo) {
        return mapper.boardUpdate(vo);
    }

    public int boardDelete(String boardNo) {
        return mapper.boardDelete(boardNo);
    }

    public List<BoardVo> searchBoard(String keyword) {
        return mapper.searchBoard(keyword);
    }

    public void hit(String boardNo) {
        mapper.hit(boardNo);
    }

    public List<BoardVo> getBoardList(String categoryNo) {
        return mapper.getBoardList(categoryNo);
    }
}
