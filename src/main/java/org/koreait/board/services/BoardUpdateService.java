package org.koreait.board.services;

import org.koreait.board.BoardSession;
import org.koreait.board.controllers.EnrollForm;
import org.koreait.board.entities.Board;
import org.koreait.board.mapper.BoardMapper;
import org.koreait.global.configs.DBConn;
import org.koreait.global.validators.Validator;
import org.mindrot.jbcrypt.BCrypt;

public class BoardUpdateService {
    private BoardMapper mapper;
    private final Validator<EnrollForm> validator;

    public BoardUpdateService(BoardMapper mapper, Validator<EnrollForm> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void process(EnrollForm form) {
        // 회원정보 수정 유효성 검사
        validator.check(form);
        Board board = new Board();
        board.setTitle(BoardSession.getBoard().getTitle());
        board.setTitle(form.getTitle());

        // 비밀번호 변경인 경우 BCrypt 해시 변환
        String content = form.getContent();
        if (content != null && !content.isBlank()) {
            board.setContent(BCrypt.hashpw(content, BCrypt.gensalt(12)));
        }

        mapper.update(board); // 수정처리

        // mybatis는 생성된 mapper 조회 결과를 캐싱하므로 이를 갱신 해야 함
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);

        // 로그인한 회원 정보도 갱신한다.
        board = mapper.get(board.getSeq()).orElse(null);
        BoardSession.setBoard(board);
    }
}
