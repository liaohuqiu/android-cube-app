package in.srain.cube.demo.app;

import android.app.Application;
import android.os.Environment;
import in.srain.cube.Cube;
import in.srain.cube.cache.CacheManagerFactory;
import in.srain.cube.demo.image.DemoDuiTangImageResizer;
import in.srain.cube.demo.utils.DemoEnv;
import in.srain.cube.diskcache.lru.SimpleDiskLruCache;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.RequestCacheManager;
import in.srain.cube.util.CLog;
import in.srain.cube.util.CubeDebug;

import java.io.File;

public class CubeDemoApplication extends Application {

    public static CubeDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        String environment = "dev";
        DemoEnv.setEnv(environment);

        // init log level
        if (DemoEnv.isProd()) {
            CLog.setLogLevel(CLog.LEVEL_ERROR);
        } else if (DemoEnv.isBeta()) {
            CLog.setLogLevel(CLog.LEVEL_WARNING);
        } else {
            // development
            CLog.setLogLevel(CLog.LEVEL_VERBOSE);
        }

        // debug options
        SimpleDiskLruCache.DEBUG = true;
        CubeDebug.DEBUG_LIFE_CYCLE = false;
        CubeDebug.DEBUG_CACHE = true;
        CubeDebug.DEBUG_IMAGE = true;
        CubeDebug.DEBUG_REQUEST = true;

        Cube.onCreate(this);

        initImageLoader();

        initRequestCache();

        // init local cache, just use default
        CacheManagerFactory.initDefaultCache(this, null, -1, -1);
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