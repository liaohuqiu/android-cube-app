package in.srain.cube.demo.app;

import android.app.Application;
import android.os.Environment;
import in.srain.cube.Cube;
import in.srain.cube.demo.image.DemoDuiTangImageResizer;
import in.srain.cube.diskcache.lru.SimpleDiskLruCache;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.RequestCacheManager;
import in.srain.cube.util.CLog;
import in.srain.cube.util.Debug;

import java.io.File;

public class CubeDemoApplication extends Application {

    public static CubeDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // init log lelve
        String environment = "dev";
        if (environment.equals("production")) {
            CLog.setLogLevel(CLog.LEVEL_ERROR);
        } else if (environment.equals("beta")) {
            CLog.setLogLevel(CLog.LEVEL_WARNING);
        } else {
            // development
            CLog.setLogLevel(CLog.LEVEL_VERBOSE);
        }

        // debug options
        SimpleDiskLruCache.DEBUG = true;
        Debug.DEBUG_LIFE_CYCLE = false;
        Debug.DEBUG_CACHE = true;
        Debug.DEBUG_IMAGE = true;
        Debug.DEBUG_REQUEST = true;

        Cube.onCreate(this);

        initImageLoader();
        initRequestCache();
    }

    private void initImageLoader() {

        File path1 = Environment.getExternalStoragePublicDirectory("cube/test1/a/b/c");
        ImageLoaderFactory.customizeCache(
                this,
                // memory size
                1024 * 10,
                // disk cache directory
                path1.getAbsolutePath(),
                // disk cache size
                ImageLoaderFactory.DEFAULT_FILE_CACHE_SIZE_IN_KB
        );

        ImageLoaderFactory.setDefaultImageResizer(DemoDuiTangImageResizer.getInstance());
    }

    private void initRequestCache() {
        String dir = "request-cache";
        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
    }

}