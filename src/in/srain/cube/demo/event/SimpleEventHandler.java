package in.srain.cube.demo.event;

import in.srain.cube.app.lifecycle.LifeCycleComponent;

public class SimpleEventHandler implements LifeCycleComponent {

    private boolean mRegistered = false;

    public SimpleEventHandler register() {
        mRegistered = true;
        EventBus.getInstance().register(this);
        return this;
    }

    public synchronized SimpleEventHandler tryToUnregister() {
        if (mRegistered) {
            mRegistered = false;
            EventBus.getInstance().unregister(this);
        }
        return this;
    }

    public synchronized SimpleEventHandler tryToRegisterIfNot() {
        if (!mRegistered) {
            register();
        }
        return this;
    }

    @Override
    public void onBecomesVisibleFromTotallyInvisible() {
    }

    @Override
    public void onBecomesPartiallyInvisible() {
    }

    @Override
    public void onBecomesVisible() {
        register();
    }

    @Override
    public void onBecomesTotallyInvisible() {
        tryToUnregister();
    }

    @Override
    public void onDestroy() {

    }
}