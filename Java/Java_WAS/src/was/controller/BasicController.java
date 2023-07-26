package was.controller;

import was.domain.HttpRequest;
import was.utils.TemplateUtil;

public class BasicController implements ArgsController {

    @Override
    public String service(HttpRequest request) {
        String responseBody = TemplateUtil.getBasicResponseBody(request);
        return TemplateUtil.httpResponse(responseBody);
    }
}
