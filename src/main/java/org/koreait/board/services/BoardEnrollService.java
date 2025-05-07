package org.koreait.board.services;

import org.koreait.board.BoardSession;
import org.koreait.board.controllers.EnrollForm;
import org.koreait.board.entities.Board;
import org.koreait.board.mapper.BoardMapper;
import org.koreait.global.validators.Validator;

public class BoardEnrollService {
    private BoardMapper mapper;
    private final Validator<EnrollForm> validator;

    public BoardEnrollService(BoardMapper mapper, Validator<EnrollForm> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }
    public Board get(Long seq) {
        if (seq == null) {
        return null;
        }
        return mapper.get(seq);
    }

    public void process(EnrollForm form) {
        // 회원 가입 데이터 유효성 검사
        validator.check(form);

        Board board = new Board();

        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        Board item = new Board();
        /**
         * seq가 있으면 수정, 없으면 추가
         */

        if (form.getSeq() > 0L) {
            item.setSeq(form.getSeq());
        } else {
            // 회원정보는 수정될 수 없고 추가시에만 등록
            board = BoardSession.getBoard();
            board.setSeq(board.getSeq());
            mapper.register(board);
        }

    }
}
