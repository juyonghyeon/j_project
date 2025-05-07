package org.koreait.board.exceptions;

import org.koreait.global.exceptions.CommonException;

public class NoDataDeleteException extends CommonException {
    public NoDataDeleteException() {
      this("삭제할 데이터가 없습니다.");
    }

    public NoDataDeleteException(String message) {
      super(message, 400);
    }
}
