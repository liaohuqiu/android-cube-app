package in.srain.cube.demo.datamodel;

import in.srain.cube.demo.event.EventCenter;
import in.srain.cube.demo.event.SliderBannerDataEvent;
import in.srain.cube.demo.request.API;
import in.srain.cube.request.JsonData;
import in.srain.cube.request.RequestData;
import in.srain.cube.request.RequestJsonHandler;
import in.srain.cube.request.SimpleRequest;

public class SliderBannerDataModel {

    public static void getSliderBanner() {

        RequestJsonHandler requestHandler = new RequestJsonHandler() {
            @Override
            public void onRequestFinish(JsonData data) {
                EventCenter.getInstance().post(new SliderBannerDataEvent().setSuccessData(data));
            }
        };

        SimpleRequest<JsonData> request = new SimpleRequest<JsonData>(requestHandler);

        RequestData requestData = request.getRequestData();
        requestData.setRequestUrl(API.API_SLIDER_BANNER);

        request.send();
    }
}
