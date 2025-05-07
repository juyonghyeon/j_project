package org.koreait.board.controllers;

import org.koreait.board.BoardSession;
import org.koreait.board.services.BoardUpdateService;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;

import java.util.List;
import java.util.Scanner;

public class BoardUpdateController extends Controller {

    private final BoardUpdateService service;

    public BoardUpdateController(BoardUpdateService service) {
        this.service = service;
        setMenus(List.of("1", "2"));
    }

    @Override
    public void common() {
        System.out.println("*************** 회원정보 수정 *******************");
    }

    @Override
    public void show() {
        System.out.println("변경할 번호를 선택하세요.");
        System.out.println("1. 회원명, 2. 휴대전화번호, 3. 비밀번호");
    }

    @Override
    public void process(String command) {
        int menu = Integer.parseInt(command);
        EnrollForm form = new EnrollForm();
        form.setEmail(BoardSession.getBoard().getEmail()); // 로그인한 회원 이메일 정보

        Scanner sc = new Scanner(System.in);
        switch(menu) {
            case 1: // 제목변경
                String title = inputEach("변경할 제목", sc);
                form.setTitle(title);
                break;
            case 2: // 내용변경
                String content = inputEach("변경할 내용", sc);
                form.setContent(content);
                break;
        }

        service.process(form); // 회원정보 수정 처리

        // 회원정보 수정 완료 후 회원정보 확인 페이지로 이동
        Router.change(BoardUpdateController.class);
    }
}
