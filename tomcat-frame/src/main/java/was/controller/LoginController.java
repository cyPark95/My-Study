package was.controller;

import was.domain.Cookie;
import was.domain.HttpStatus;
import was.utils.CookieSessionUtil;
import was.utils.TemplateUtil;

public class LoginController implements NonArgsController {

    @Override
    public String service() {
        String responseBody = TemplateUtil.getResponseBody("로그인 페이지입니다.");
        return TemplateUtil.httpResponseWithCookie(
                HttpStatus.OK,
                new Cookie(CookieSessionUtil.COOKIE_SESSION_NAME, CookieSessionUtil.createSession("login")),
                responseBody
        );
    }
}
