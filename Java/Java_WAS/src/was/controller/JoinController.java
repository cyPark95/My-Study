package was.controller;

import was.utils.HtmlTemplate;

public class JoinController implements NonArgsController {

    @Override
    public String service() {
        return HtmlTemplate.getResponseBody("회원가입 페이지입니다.");
    }
}
