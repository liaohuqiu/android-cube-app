package in.srain.cube.demo.request;

public class DemoRequestManager {

    private static DemoRequestManager sInstance;

    public static DemoRequestManager getInstance() {
        if (sInstance == null) {
            sInstance = new DemoRequestManager();
        }
        return sInstance;
    }

    public void prepareRequest(in.srain.cube.request.RequestBase request) {

        // you can add some basic data to here
        String token = "";
        request.getRequestData().addQueryData("token", token);
    }
}
