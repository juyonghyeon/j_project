package org.koreait.board.services;

import org.koreait.board.mapper.BoardMapper;
import org.koreait.board.validators.BoardSaveValidator;
import org.koreait.global.configs.DBConn;
import org.koreait.global.services.Bean;

public class BoardService {
    @Bean
    public BoardMapper boardMapper() {
        return DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    @Bean
    public BoardSaveValidator boardSaveValidator() {
        return new BoardSaveValidator();
    }

    @Bean
    public BoardEnrollService enrollService() {
        return new BoardEnrollService(boardMapper(), boardSaveValidator());
    }


}
