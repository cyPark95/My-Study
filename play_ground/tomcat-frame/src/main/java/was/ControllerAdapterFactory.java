package was;

import was.adapter.ArgsControllerAdapter;
import was.adapter.ControllerAdepter;
import was.adapter.NonArgsControllerAdapter;
import was.controller.BasicController;
import was.controller.JoinController;
import was.controller.LoginController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerAdapterFactory {

    private final Map<String, Object> handlerMap = new HashMap<>();
    private final List<ControllerAdepter> adepterList = new ArrayList<>();

    public ControllerAdapterFactory() {
        initControllerMap();
        intiAdepterList();
    }

    public Object getHandler(String path) {
        return handlerMap.get(path);
    }

    public ControllerAdepter getAdapter(Object controller) {
        for (ControllerAdepter adepter : adepterList) {
            if (adepter.supports(controller)) {
                return adepter;
            }
        }

        return null;
    }

    private void initControllerMap() {
        handlerMap.put("/", new BasicController());
        handlerMap.put("/join", new JoinController());
        handlerMap.put("/login", new LoginController());
    }

    private void intiAdepterList() {
        adepterList.add(new ArgsControllerAdapter());
        adepterList.add(new NonArgsControllerAdapter());
    }
}
