package in.srain.cube.demo.request;

import in.srain.cube.request.RequestHandler;

public class DemoSimpleRequest<T> extends in.srain.cube.request.SimpleRequest<T> {

    public DemoSimpleRequest(RequestHandler<T> handler) {
        super(handler);
    }

    @Override
    protected void prepareRequest() {
        DemoRequestManager.getInstance().prepareRequest(this);
    }
}
