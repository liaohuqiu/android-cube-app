package in.srain.cube.demo.app;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import in.srain.cube.Cube;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.JsonData;
import in.srain.cube.request.RequestCacheManager;
import in.srain.cube.demo.image.DemoDuiTangImageResizer;
import in.srain.cube.request.RequestDefaultHandler;
import in.srain.cube.request.SimpleRequest;
import in.srain.cube.util.CLog;
import in.srain.cube.util.Debug;

public class CubeDemoApplication extends Application {

    public static CubeDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        String environment = "";
        if (environment.equals("production")) {
            CLog.setLogLevel(CLog.LEVEL_ERROR);
        } else if (environment.equals("beta")) {
            CLog.setLogLevel(CLog.LEVEL_WARNING);
        } else {
            // development
            CLog.setLogLevel(CLog.LEVEL_VERBOSE);
        }

        CLog.d("sample", "Here is a debug message");
        CLog.d("sample", "Here is a debug message with parameters: %d", 1);
        Exception ex = new RuntimeException();
        CLog.d("sample", "Here is a debug message with parameters: %s", ex);

        Debug.DEBUG_IMAGE = true;
        // CLog.DEBUG_CACHE = true;
        // CLog.DEBUG_SCROLL_HEADER_FRAME = true;
        // CLog.DEBUG_PTR_FRAME = true;
        ImageLoaderFactory.setDefaultImageResizer(DemoDuiTangImageResizer.getInstance());
        String dir = "request-cache";
        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
        Cube.onCreate(this);
    }
}