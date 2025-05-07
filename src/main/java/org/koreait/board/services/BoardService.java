package org.koreait.board.services;

import org.koreait.board.mappers.BoardMapper;
import org.koreait.board.validators.BoardEnrollValidator;
import org.koreait.global.configs.DBConn;
import org.koreait.global.services.Bean;
import org.koreait.global.services.Configuration;

@Configuration
public class BoardService {
    @Bean
    public BoardMapper boardMapper() {
        return DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    @Bean
    public BoardEnrollValidator boardSaveValidator() {
        return new BoardEnrollValidator();
    }

    @Bean
    public BoardEnrollService enrollService() {
        return new BoardEnrollService(boardMapper(), boardSaveValidator());
    }

    @Bean
    public BoardInfoService infoService() {
        return new BoardInfoService();
    }

    @Bean
    public BoardDeleteService boardDeleteService() {
        BoardMapper mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
        return new BoardDeleteService(mapper);
    }
}
