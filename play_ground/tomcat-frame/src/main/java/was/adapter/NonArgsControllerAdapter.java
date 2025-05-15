package was.adapter;

import was.controller.NonArgsController;
import was.domain.HttpRequest;

public class NonArgsControllerAdapter implements ControllerAdepter {

    @Override
    public boolean supports(Object handle) {
        return handle instanceof NonArgsController;
    }

    @Override
    public String handle(Object handler, HttpRequest request) {
        NonArgsController controller = (NonArgsController) handler;
        return controller.service();
    }
}
