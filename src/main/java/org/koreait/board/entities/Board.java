package org.koreait.board.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private int seq;
    private String title;
    private String content;
    private LocalDateTime enrDt;
}

