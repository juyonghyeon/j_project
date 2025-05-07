package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.configs.DBConn;
import org.koreait.global.paging.SearchForm;
import org.koreait.board.exceptions.BoardNotFoundException;

import java.util.List;

public class BoardInfoService  {

    private BoardMapper mapper;


    public BoardInfoService() {
        updateMapper();
    }

    public void updateMapper() {
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    /**
     * 이메일 주소로 회원 1명 조회
     *
     * @param email
     * @return
     */

    public Board get(String email) {
        updateMapper();
        return mapper.get(email).orElseThrow(BoardNotFoundException::new);
    }

    /**
     * 회원 목록 조회
     *
     * @return
     */
    public List<Board> getList(SearchForm search) {
        updateMapper();
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit;

        search.setOffset(offset);
        search.setPage(page);
        search.setLimit(limit);

        return mapper.getList(search);
    }
}
