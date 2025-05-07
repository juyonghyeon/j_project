package org.koreait.board;

import org.koreait.board.entities.Board;

/**
 * 로그인한 회원 정보를 보관하는 공간
 * member 값이 있다면 로그인 상태
 */
public class BoardSession {
    private static Board board;

    public static void setMember(Board board) {
        BoardSession.board = board;
    }

    // 게시글 정보 조회
    public static Board getBoard() {
        return board;
    }

    // 로그인 여부
    public static boolean isLogin() {
        return board != null;
    }

    // 로그아웃 처리
    public static void logout() {
        board = null;
    }
}
