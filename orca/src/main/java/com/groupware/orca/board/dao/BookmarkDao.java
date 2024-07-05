package com.groupware.orca.board.dao;

import com.groupware.orca.board.mapper.BookmarkMapper;
import com.groupware.orca.board.vo.BookmarkVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookmarkDao {

    private final BookmarkMapper bookmarkMapper;

    public List<BookmarkVo> getBookmarksByEmpNo(int empNo) {
        return bookmarkMapper.getBookmarksByEmpNo(empNo);
    }

    public int insertBookmark(BookmarkVo bookmarkVo) {
        return bookmarkMapper.insertBookmark(bookmarkVo);
    }

    public int deleteBookmarkByBoardNoAndEmpNo(int boardNo, int empNo) {
        return bookmarkMapper.deleteBookmarkByBoardNoAndEmpNo(boardNo, empNo);
    }
}
