package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.BoardFileDao;
import com.groupware.orca.board.vo.BoardFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFileService {


    private  final BoardFileDao dao;

    public List<BoardFileVo> getFilesByBoardNo(Long boardNo) {
        return dao.getFilesByBoardNo(boardNo);
    }

    public int insertFile(BoardFileVo file) {
        return dao.insertFile(file);
    }

    public int deleteFile(Long fileNo) {
        return dao.deleteFile(fileNo);
    }
}
