package com.groupware.orca.board.dao;

import com.groupware.orca.board.mapper.BoardMapper;
import com.groupware.orca.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final BoardMapper mapper;

    public BoardVo getBoardDetail(int boardNo) {
        return mapper.getBoardDetail(boardNo);
    }

    public int boardInsert(BoardVo vo) {
        return mapper.boardInsert(vo);
    }

    public int boardUpdate(BoardVo vo) {
        return mapper.boardUpdate(vo);
    }

    public int boardDelete(int boardNo) {
        return mapper.boardDelete(boardNo);
    }

    public List<BoardVo> searchBoard(String title, int categoryNo) {
        return mapper.searchBoard(title, categoryNo);
    }

    public void hit(int boardNo) {
        mapper.hit(boardNo);
    }

    public List<BoardVo> getBoardList(int categoryNo) {
        return mapper.getBoardList(categoryNo);
    }

    public List<Map<String, Object>> getStatsByDate() {
        return mapper.getStatsByDate();
    }
}
