package in.srain.cube.demo.datamodel;

import in.srain.cube.concurrent.SimpleExecutor;
import in.srain.cube.request.*;
import in.srain.cube.util.CLog;

public class DemoUploadModel {

    public static void testUpload() {

        RequestHandler<JsonData> requestHandler = new RequestHandler<JsonData>() {
            @Override
            public JsonData processOriginData(JsonData jsonData) {
                return jsonData;
            }

            @Override
            public void onRequestFail(FailData failData) {
                CLog.d("demo-request", "onRequestFail: %s", failData);
            }

            @Override
            public void onRequestFinish(JsonData data) {
                CLog.d("demo-request", "onRequestFinish: %s", data);
            }
        };

        final SimpleRequest<JsonData> request = new SimpleRequest<JsonData>();
        request.setRequestHandler(requestHandler);

        String file = "/storage/emulated/0/Android/data/com.skykiwi.app/cache/skykiwi_thumb_pics/t7u32b_20150220_073237.jpg";
        String url = "http://skykiwi-demo.liaohuqiu.net/api/upload.php";
        RequestData requestData = request.getRequestData();
        requestData.setRequestUrl(url);
        // requestData.addFile("f1", file);
        requestData.addHeader("header1", "header1 data");

        SimpleExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                JsonData data = request.requestSync();
                CLog.d("demo-request", "requestSync: %s", data);
            }
        });

        // request.send();
    }
}
