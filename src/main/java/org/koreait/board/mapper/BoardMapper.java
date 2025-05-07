package org.koreait.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.koreait.board.entities.Board;
import org.koreait.global.paging.SearchForm;

import java.util.List;
import java.util.Optional;

public interface BoardMapper {
    int register(Board board);
    List<Board> getList(SearchForm search);
    Optional<Board> get(String email);
    Board get(long seq);


    @Select("SELECT COUNT(*) FROM board WHERE seq=#{seq}")
    int exists(long seq);


    int update(Board board);

    int delete(long seq);
}
