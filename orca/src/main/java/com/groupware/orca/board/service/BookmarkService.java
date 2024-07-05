package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.BookmarkDao;
import com.groupware.orca.board.vo.BookmarkVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {

    private final BookmarkDao bookmarkDao;

    public List<BookmarkVo> getBookmarksByEmpNo(int empNo) {
        return bookmarkDao.getBookmarksByEmpNo(empNo);
    }

    public int insertBookmark(BookmarkVo bookmarkVo) {
        return bookmarkDao.insertBookmark(bookmarkVo);
    }

    public int deleteBookmarkByBoardNoAndEmpNo(int boardNo, int empNo) {
        return bookmarkDao.deleteBookmarkByBoardNoAndEmpNo(boardNo, empNo);
    }
}
