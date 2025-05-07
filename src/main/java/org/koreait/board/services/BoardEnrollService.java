package org.koreait.board.services;

import org.apache.ibatis.annotations.Mapper;
import org.koreait.board.controllers.EnrollForm;
import org.koreait.global.configs.DBConn;
import org.koreait.global.validators.Validator;
import org.koreait.member.controllers.JoinForm;
import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
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

        // 유효성 검사 통과했다면 DB 처리 S
        // 휴대전화번호는 검색의 편의상 숫자를 제외하고는 전부 제거
        String mobile = form.getMobile();
        if (mobile != null && !mobile.isBlank()) {
            mobile = mobile.replaceAll("\\D", "");
        }

        Board member = new Board();
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setMobile(mobile);
        member.setTerms(form.isTerms());


        // 유효성 검사 통과했다면 DB 처리 E

        // mybatis는 생성된 mapper 조회 결과를 캐싱하므로 이를 갱신 해야 함
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }
}
