package was.controller;

import was.domain.HttpRequest;
import was.utils.HtmlTemplate;

public class BasicController implements ArgsController {

    @Override
    public String service(HttpRequest request) {
        return HtmlTemplate.getBasicResponse(request);
    }
}
