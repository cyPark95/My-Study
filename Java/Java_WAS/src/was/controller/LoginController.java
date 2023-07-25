package was.controller;

import was.utils.HtmlTemplate;

public class LoginController implements NonArgsController {

    @Override
    public String service() {
        return HtmlTemplate.getResponseBody("로그인 페이지입니다.");
    }
}
