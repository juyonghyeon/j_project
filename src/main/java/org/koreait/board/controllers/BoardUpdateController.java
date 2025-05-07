package org.koreait.board.controllers;

import org.koreait.board.entities.Board;
import org.koreait.board.services.BoardEnrollService;
import org.koreait.board.services.BoardInfoService;
import org.koreait.board.services.BoardUpdateService;
import org.koreait.global.router.Router;
import org.koreait.global.router.Controller;

import java.util.List;
import java.util.Scanner;

public class BoardUpdateController extends Controller {
    private static long seq; // 게시글 번
    private final BoardInfoService infoService;
    private final BoardEnrollService enrollService;

    public BoardUpdateController(BoardInfoService infoService, BoardEnrollService enrollService) {
        this.infoService = infoService;
        this.enrollService = enrollService;
        setMenus(List.of("1", "2", "3"));
    }

    public static void setSeq(long seq) {
        BoardUpdateController.seq = seq;
    }

    @Override
    public void common() {
        System.out.println("*************** 게시글 수정 *******************");
    }

    @Override
    public void show() {
        System.out.println("수정할 항목을 선택하세요(m - 메인메뉴, q - 종료).");
        System.out.println("1. 작성자, 2. 제목, 3. 내용");
    }
    @Override
    public void process(String command) {
        int menu = Integer.parseInt(command);
        Board item = infoService.get(seq);
        EnrollForm form = new EnrollForm();
        form.setSeq(item.getSeq());

        Scanner sc = new Scanner(System.in);
        String str = inputEach("변경내용 입력", sc);

        switch (menu) {
            case 1: // 작성자
                form.setName(str); break;
            case 2: // 제목
                form.setTitle(str); break;
            case 3: // 내용
                form.setContent(str); break;
        }

        enrollService.process(form);

        // 수정 완료 후 게시글 보기로 이동
        BoardViewController.setSeq(seq);
        Router.change(BoardViewController.class);
    }
}
