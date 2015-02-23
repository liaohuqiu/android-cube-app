package in.srain.cube.demo.datamodel;

import in.srain.cube.demo.event.EventBus;
import in.srain.cube.demo.request.DemoCacheRequest;
import in.srain.cube.request.*;

public class CacheAbleRequestData {

    public static void getImage(boolean useCacheAnyway, boolean disableCache) {

        DemoCacheRequest<JsonData> request = new DemoCacheRequest<JsonData>();
        CacheAbleRequestHandler<JsonData> handler = new CacheAbleRequestDefaultHandler<JsonData>() {

            @Override
            public JsonData processOriginData(JsonData jsonData) {
                EventBus.getInstance().post(new MsgDataEvent("processOriginData"));
                return jsonData;
            }

            @Override
            public void onCacheAbleRequestFinish(JsonData data, CacheAbleRequest.ResultType type, boolean outOfDate) {
                EventBus.getInstance().post(new MsgDataEvent(
                        "onCacheAbleRequestFinish, result type: %s, out of date: %s", type, outOfDate));
                EventBus.getInstance().post(new MsgDataEvent(
                        "time: %s", data.optJson("data").optString("time")));
            }

            @Override
            public void onCacheData(JsonData data, boolean outOfDate) {
                super.onCacheData(data, outOfDate);
                EventBus.getInstance().post(new MsgDataEvent(
                        "onCacheData, out of date: %s", outOfDate));
            }

            @Override
            public void onRequestFail(FailData failData) {
            }
        };

        request.setCacheAbleRequestHandler(handler);
        request.setUseCacheAnyway(useCacheAnyway);
        request.setTimeout(1000);

        String cacheKey = "api/get-server-time";
        request.setCacheTime(30).setCacheKey(cacheKey);
        request.setDisableCache(disableCache);

        request.getRequestData().setRequestUrl(ConfigData.API_URL_PRE + "/get-image");
        request.send();
    }

    public static class MsgDataEvent {
        public String msg;

        public MsgDataEvent(String format, Object... args) {
            if (args.length > 0) {
                msg = String.format(format, args);
            } else {
                msg = format;
            }
        }
    }
}
