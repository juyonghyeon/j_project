package org.koreait.board.controllers;

import lombok.Data;

@Data
public class EnrollForm {
    private long seq;
    private String name;
    private String email;
    private String confirmIsAdmin;
    private String title;
    private String content;
}
