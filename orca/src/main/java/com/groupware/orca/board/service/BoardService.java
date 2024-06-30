package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.BoardDao;
import com.groupware.orca.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao dao;

    public BoardVo getBoardDetail(int boardNo) {
        return dao.getBoardDetail(boardNo);
    }

    public int boardInsert(BoardVo vo) {
        return dao.boardInsert(vo);
    }

    public int boardUpdate(BoardVo vo) {
        return dao.boardUpdate(vo);
    }

    public int boardDelete(int boardNo) {
        return dao.boardDelete(boardNo);
    }

    public List<BoardVo> searchBoard(String title, int categoryNo) {
        return dao.searchBoard(title, categoryNo);
    }

    public void hit(int boardNo) {
        dao.hit(boardNo);
    }

    public List<BoardVo> getBoardList(int categoryNo) {
        return dao.getBoardList(categoryNo);
    }

    public List<Map<String, Object>> getStatsByDate() {
        return dao.getStatsByDate();
    }
}
