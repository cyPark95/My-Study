package was.controller;

import was.domain.HttpStatus;
import was.utils.TemplateUtil;

public class JoinController implements NonArgsController {

    @Override
    public String service() {
        String responseBody = TemplateUtil.getResponseBody("회원가입 페이지입니다.");
        return TemplateUtil.httpResponse(HttpStatus.OK, responseBody);
    }
}
