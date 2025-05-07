package org.koreait.board.controllers;

import org.koreait.board.services.BoardEnrollService;
import org.koreait.board.validators.BoardEnrollValidator;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;

import java.util.Scanner;

import static org.koreait.member.MemberSession.member;

public class BoardEnrollController extends Controller {
    private final BoardEnrollService service;

    public BoardEnrollController (BoardEnrollService service) {
        this.service = service;

        EnrollForm form = new EnrollForm();
        setPrompt(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    String email = inputEach("1. 이메일", sc);
                    form.setEmail(email);

                    String title = inputEach("2. 제목", sc);
                    form.setTitle(title);

                    String content = inputEach("3. 내용", sc);
                    form.setContent(content);

                    service.process(form);
                    break;
                } catch (CommonException e) {
                    printError(e);
                }
            }

            // Router.change(Board??.class); 목록으로 넘어가기
        });
    }
}
