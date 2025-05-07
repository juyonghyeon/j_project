package org.koreait.board.services;

import org.apache.ibatis.annotations.Mapper;
import org.koreait.board.controllers.EnrollForm;
import org.koreait.global.configs.DBConn;
import org.koreait.global.validators.Validator;
import org.koreait.member.MemberSession;
import org.koreait.member.controllers.JoinForm;
import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.member.entities.Member;
import org.koreait.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;

public class BoardEnrollService {
    private BoardMapper mapper;
    private final Validator<EnrollForm> validator;  // 유효성 검사를 위한 Validator


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

        if (form.getSeq() > 0L) {
            item.setSeq(form.getSeq());
        } else {
            // 회원정보는 수정될 수 없고 추가시에만 등록
            Member member = MemberSession.getMember();
            board.setMemberSeq(member.getSeq());
        }




    }
}
