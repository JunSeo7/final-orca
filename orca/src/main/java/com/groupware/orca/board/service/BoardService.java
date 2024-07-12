package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.BoardDao;
import com.groupware.orca.board.vo.BoardPenaltyVo;
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
        if ("Y".equals(boardVo.getIsAnonymous())) {  // null 체크 및 익명 처리
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

    public List<BoardVo> searchBoard(String title, int categoryNo, int page, int rows) {
        int offset = (page - 1) * rows;
        return dao.searchBoard(title, categoryNo, offset, rows);
    }

    public int getSearchCount(String title, int categoryNo) {
        return dao.getSearchCount(title, categoryNo);
    }

    public void hit(int boardNo) {
        dao.hit(boardNo);
    }

    public List<BoardVo> getBoardList(int categoryNo, int page, int rows) {
        int offset = (page - 1) * rows;
        List<BoardVo> boardList = dao.getBoardList(categoryNo, offset, rows);
        for (BoardVo board : boardList) {
            if ("Y".equals(board.getIsAnonymous())) {  // null 체크 및 익명 처리
                board.setEmployeeName("***");
            }
        }
        return boardList;
    }

    public int getBoardCount(int categoryNo) {
        return dao.getBoardCount(categoryNo);
    }

    public List<Map<String, Object>> getStatsByDate() {
        return dao.getStatsByDate();
    }

    public int addLike(int boardNo, int empNo) {
        return dao.addLike(boardNo, empNo);
    }

    public int removeLike(int boardNo, int empNo) {
        return dao.removeLike(boardNo, empNo);
    }

    public boolean isLiked(int boardNo, int empNo) {
        return dao.isLiked(boardNo, empNo);
    }

    public int getLikeCount(int boardNo) {
        return dao.getLikeCount(boardNo);
    }

    public int deleteLikesByBoardNo(int boardNo) {
        return dao.deleteLikesByBoardNo(boardNo);
    }

    public int reportBoard(int boardNo, int categoryNo, String content, int empNo) {
        System.out.println("Service - Reporting boardNo: " + boardNo);
        BoardPenaltyVo penalty = new BoardPenaltyVo();
        penalty.setBoardNo(boardNo);
        penalty.setPenaltyCategoryNo(categoryNo);
        penalty.setPenaltyContent(content);
        penalty.setEmpNo(empNo);
        return    dao.insertPenalty(penalty);
    }

    public boolean checkAndHideBoard(int boardNo) {
        int penaltyCount = dao.countPenaltiesByBoardNo(boardNo);
        if (penaltyCount >= 3) { // 신고가 3회 이상이면 숨김 처리
            dao.hideBoard(boardNo);
            return true;
        }
        return false;
    }

    public List<Map<String, Object>> getPenaltyCategories() {
        return dao.getPenaltyCategories();
    }

    public int deletePenaltyByBoardNo(int boardNo) {
        return dao.deletePenaltyByBoardNo(boardNo);

    }
}
