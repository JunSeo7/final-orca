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

    public List<BoardVo> searchBoard(String title, int categoryNo, int offset, int rows) {
        return mapper.searchBoard(title, categoryNo, offset, rows);
    }

    public int getSearchCount(String title, int categoryNo) {
        return mapper.getSearchCount(title, categoryNo);
    }

    public void hit(int boardNo) {
        mapper.hit(boardNo);
    }

    public List<BoardVo> getBoardList(int categoryNo, int offset, int rows) {
        return mapper.getBoardList(categoryNo, offset, rows);
    }

    public int getBoardCount(int categoryNo) {
        return mapper.getBoardCount(categoryNo);
    }

    public List<Map<String, Object>> getStatsByDate() {
        return mapper.getStatsByDate();
    }

    public int addLike(int boardNo, int empNo) {
        return mapper.addLike(boardNo, empNo);
    }

    public int removeLike(int boardNo, int empNo) {
        return mapper.removeLike(boardNo, empNo);
    }

    public boolean isLiked(int boardNo, int empNo) {
        return mapper.isLiked(boardNo, empNo) > 0;
    }

    public int getLikeCount(int boardNo) {
        return mapper.getLikeCount(boardNo);
    }
    public int deleteLikesByBoardNo(int boardNo) {
        return mapper.deleteLikesByBoardNo(boardNo);
}
    }