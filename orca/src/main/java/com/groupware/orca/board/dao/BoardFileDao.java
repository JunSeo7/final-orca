package com.groupware.orca.board.dao;

import com.groupware.orca.board.mapper.BoardFileMapper;
import com.groupware.orca.board.vo.BoardFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardFileDao {


    private final BoardFileMapper mapper;

    public List<BoardFileVo> getFilesByBoardNo(Long boardNo) {
        return mapper.getFilesByBoardNo(boardNo);
    }

    public int insertFile(BoardFileVo file) {
        return mapper.insertFile(file);
    }

    public int deleteFile(Long fileNo) {
        return mapper.deleteFile(fileNo);
    }
}
