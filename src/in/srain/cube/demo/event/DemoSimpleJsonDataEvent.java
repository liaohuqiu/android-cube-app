package in.srain.cube.demo.event;

import in.srain.cube.request.JsonData;

public class DemoSimpleJsonDataEvent {
    public JsonData data;
    public boolean success;
    public String message;

    public DemoSimpleJsonDataEvent setSuccessData(JsonData jsonData) {
        success = true;
        data = jsonData;
        return this;
    }

    public DemoSimpleJsonDataEvent setFailMessage(String message) {
        success = false;
        this.message = message;
        return this;
    }
}
