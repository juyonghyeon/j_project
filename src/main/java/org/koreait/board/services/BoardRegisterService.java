package org.koreait.board.services;

import org.koreait.board.BoardSession;
import org.koreait.board.entities.Board;
import org.koreait.global.validators.Validator;
import org.koreait.board.controllers.EnrollForm;

/**
 * 로그인 처리 서비스
 *
 */
public class BoardRegisterService {
    private final Validator<EnrollForm> validator;
    private final BoardInfoService infoService;

    // 의존성 주입
    public BoardEnrollService(Validator<EnrollForm> validator, BoardInfoService infoService) {
        this.validator = validator;
        this.infoService = infoService;
    }

    // 로그인 처리
    public void process(EnrollForm form) {

        // 유효성 검사
        validator.check(form);

        // 로그인 처리 S
        Board board = infoService.get(form.getSeq());
        BoardSession.setBoard(board);
        // 로그인 처리 E
    }
}
