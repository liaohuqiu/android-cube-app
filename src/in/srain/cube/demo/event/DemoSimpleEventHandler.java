package in.srain.cube.demo.event;

import in.srain.cube.app.lifecycle.LifeCycleComponent;

public class DemoSimpleEventHandler implements LifeCycleComponent {

    private boolean mRegistered = false;

    public DemoSimpleEventHandler register() {
        if (!mRegistered) {
            mRegistered = true;
            EventCenter.getInstance().register(this);
        }
        return this;
    }

    public synchronized DemoSimpleEventHandler tryToUnregister() {
        if (mRegistered) {
            mRegistered = false;
            EventCenter.getInstance().unregister(this);
        }
        return this;
    }

    public synchronized DemoSimpleEventHandler tryToRegisterIfNot() {
        register();
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