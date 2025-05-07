package org.koreait.board.validators;

import org.koreait.board.controllers.EnrollForm;
import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;

public class BoardUpdateValidator implements Validator<EnrollForm>, RequiredFieldValidator {
    @Override
    public void check(EnrollForm form) {

        String title = form.getTitle();
        if (title != null && !title.isBlank()) {
            checkTrue(title.length() <= 10, "제목은 10글자보다 짧아야 합니다.");
        }

        String content = form.getContent();
        if (content != null && !content.isBlank()) {
            checkTrue(content.length() >= 30, "내용은 30글자보다 길어야 합니다.");
        }
    }
}
