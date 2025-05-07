package org.koreait.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.koreait.board.entities.Board;
import org.koreait.global.paging.SearchForm;

import java.util.List;
import java.util.Optional;

public interface BoardMapper {
    int register(Board board);
    List<Board> getList(SearchForm search);
    Optional<Board> get(String sql);

    @Select("SELECT COUNT(*) FROM member WHERE sql=#{sql}")
    int exists( String sql);

    // 회원정보 수정
    int update(Board board);
}
