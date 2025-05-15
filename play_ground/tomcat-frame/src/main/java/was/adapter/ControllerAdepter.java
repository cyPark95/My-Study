package was.adapter;

import was.domain.HttpRequest;

public interface ControllerAdepter {

    boolean supports(Object handle);

    String handle(Object handler, HttpRequest request);
}
