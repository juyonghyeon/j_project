package org.koreait.board.services;

import org.koreait.board.controllers.EnrollForm;
import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.configs.DBConn;
import org.koreait.global.validators.Validator;

public class BoardUpdateService {
    private BoardMapper mapper;
    private final Validator<EnrollForm> validator;

    public BoardUpdateService(BoardMapper mapper, Validator<EnrollForm> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void process(EnrollForm form) {
        /*
        // 수정 유효성 검사
        validator.check(form);
        Board board = new Board();
        board.setTitle(form.getTitle());

        mapper.update(board); // 수정처리

        // mybatis는 생성된 mapper 조회 결과를 캐싱하므로 이를 갱신 해야 함
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);


        BoardSession.setBoard(board); */
    }
}
