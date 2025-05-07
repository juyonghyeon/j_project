package org.koreait.board.validator;

import org.koreait.board.controllers.EnrollForm;
import org.koreait.board.entities.Board;
import org.koreait.board.services.BoardEnrollService;
import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;
import org.koreait.board.mapper.BoardMapper;

/**
 * 로그인 유효성 검사
 *
 */
public class BoardEnrollValidator implements Validator<EnrollForm>, RequiredFieldValidator {
    private final BoardEnrollService enrollService;

    public BoardEnrollValidator(BoardEnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @Override
    public void check(EnrollForm form) {
        String email = form.getEmail();
        String title = form.getTitle();

        // 필수항목 검증 - 이메일, 비밀번호
        checkString(email, "이메일을 입력하세요.");

        String message = "게시글이 없습니다.";

        // 등록된 게시 글인지 검증
        Board board = enrollService.equals(seq);
        checkTrue(board != null, message);

        // 이메일 일치여부 체크
        checkTrue(check(email == Board.getEmail()), message);
    }
}
