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
     * 게시글 번호
     *
     * @param seq
     * @return
     */

    public Board get(long seq) {
        updateMapper();
        return mapper.get(seq).orElseThrow(BoardNotFoundException::new);
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
