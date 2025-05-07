package org.koreait.board.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private long seq;
    private long memberSeq;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
    private String email;
    private String name;
}

