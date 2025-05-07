package org.koreait.board.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private long seq;
    private long memberSeq;
    private String title;
    private String content;
    private String email;
    private LocalDateTime enrDt;
}

