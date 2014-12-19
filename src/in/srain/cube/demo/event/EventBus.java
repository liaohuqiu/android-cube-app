package in.srain.cube.demo.event;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import in.srain.cube.app.lifecycle.LifeCycleComponentManager;

public class EventBus {

    private static final Bus instance = new Bus(ThreadEnforcer.ANY);

    private EventBus() {
    }

    public static SimpleEventHandler bindContainerAndHandler(Object container, SimpleEventHandler handler) {
        LifeCycleComponentManager.tryAddComponentToContainer(handler, container);
        return handler;
    }

    public static final Bus getInstance() {
        return instance;
    }
}
