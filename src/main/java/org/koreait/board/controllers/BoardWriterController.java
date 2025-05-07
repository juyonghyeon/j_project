package org.koreait.board.controllers;

import org.koreait.board.services.BoardEnrollService;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;
import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;

import java.util.Scanner;

public class BoardWriterController extends Controller {

    private final BoardEnrollService service;
    public BoardWriterController(BoardEnrollService service) {
        this.service = service;

        Scanner sc = new Scanner(System.in);
        EnrollForm form = new EnrollForm();

        setPrompt(() -> {
            Member member = MemberSession.getMember();
            form.setEmail(member.getEmail());

            while(true) {
                try {
                    String title = inputEach("1. 제목", sc);
                    form.setTitle(title);

                    String content = inputEach("2. 내용", sc);
                    form.setContent(content);
                    service.process(form);
                    break;
                } catch (CommonException e) {
                    printError(e);
                }
            }
            Router.change(BoardListController.class);
        });
    }

    @Override
    public void show() {
        System.out.println("게시글을 작성하기 위한 내용을 입력하세요(m - 메인메뉴, q - 종료).");
    }

    @Override
    public void common() {
        System.out.println("************************ 게시글 작성하기 ************************");
    }
}
