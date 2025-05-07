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

    /**
     * 의존성 주입
     *
     * @param mapper
     * @param validator
     */
    public BoardEnrollService(BoardMapper mapper, Validator<EnrollForm> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    /**
     * 회원 가입 처리
     *
     */
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

        Board member = new Board();
        member.setEmail(form.getEmail());
        member.setSeq(form.getSeq());
        member.setTitle(form.getTitle());
        member.setContent(form.getContent());


        // 유효성 검사 통과했다면 DB 처리 E

        // mybatis는 생성된 mapper 조회 결과를 캐싱하므로 이를 갱신 해야 함
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }
}
