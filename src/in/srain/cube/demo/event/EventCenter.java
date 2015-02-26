package in.srain.cube.demo.event;

import de.greenrobot.event.EventBus;
import in.srain.cube.app.lifecycle.LifeCycleComponentManager;

public class EventCenter {

    private static final EventBus instance = new EventBus();

    private EventCenter() {
    }

    public static DemoSimpleEventHandler bindContainerAndHandler(Object container, DemoSimpleEventHandler handler) {
        LifeCycleComponentManager.tryAddComponentToContainer(handler, container);
        return handler;
    }

    public static final EventBus getInstance() {
        return instance;
    }
}
