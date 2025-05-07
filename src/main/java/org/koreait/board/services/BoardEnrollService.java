package org.koreait.board.services;

import org.koreait.board.controllers.EnrollForm;
import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.Validator;
import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;

public class BoardEnrollService {
    private BoardMapper mapper;
    private final Validator<EnrollForm> validator;

    public BoardEnrollService(BoardMapper mapper, Validator<EnrollForm> validator) {
        this.mapper = mapper;
        this.validator = validator;
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

        if (form.getSeq() > 0L) { // 수정 
            item.setSeq(form.getSeq());
        } else { // 추가
            Member member = MemberSession.getMember();
            board.setMemberSeq(member.getSeq());
            mapper.register(board);
        }

    }
}
