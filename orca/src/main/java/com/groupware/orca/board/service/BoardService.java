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
        BoardVo boardVo = dao.getBoardDetail(boardNo);
        if (boardVo.getIsAnonymous() == "Y") {
            boardVo.setEmployeeName("***");
        }
        return boardVo;
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
        List<BoardVo> boardList = dao.searchBoard(title, categoryNo);
        return boardList;
    }

    public void hit(int boardNo) {
        dao.hit(boardNo);
    }

    public List<BoardVo> getBoardList(int categoryNo) {
        List<BoardVo> boardList = dao.getBoardList(categoryNo);
        for (BoardVo board : boardList) {
            if (board.getIsAnonymous() == "Y") {
                board.setEmployeeName("***");
            }
        }
        return boardList;
    }

    public List<Map<String, Object>> getStatsByDate() {
        return dao.getStatsByDate();
    }


}
