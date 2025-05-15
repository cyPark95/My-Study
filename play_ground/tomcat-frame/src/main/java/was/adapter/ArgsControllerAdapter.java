package was.adapter;

import was.controller.ArgsController;
import was.domain.HttpRequest;

public class ArgsControllerAdapter implements ControllerAdepter {

    @Override
    public boolean supports(Object handle) {
        return handle instanceof ArgsController;
    }

    @Override
    public String handle(Object handler, HttpRequest request) {
        ArgsController controller = (ArgsController) handler;
        return controller.service(request);
    }
}
