package in.srain.cube.demo.app;

import android.app.Application;
import android.os.Environment;
import in.srain.cube.Cube;
import in.srain.cube.cache.CacheManagerFactory;
import in.srain.cube.demo.BuildConfig;
import in.srain.cube.demo.image.DemoDuiTangImageReSizer;
import in.srain.cube.demo.utils.DemoEnv;
import in.srain.cube.diskcache.lru.SimpleDiskLruCache;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.impl.DefaultImageLoadHandler;
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

        boolean isProd = !BuildConfig.DEBUG;
        DemoEnv.setIsProd(isProd);

        // init log level
        if (DemoEnv.isProd()) {
            CLog.setLogLevel(CLog.LEVEL_ERROR);
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
                // path1.getAbsolutePath(),
                null,
                // disk cache size
                ImageLoaderFactory.DEFAULT_FILE_CACHE_SIZE_IN_KB
        );

        DefaultImageLoadHandler handler = new DefaultImageLoadHandler(this);
        // handler.setLoadingImageColor("#999999");

        ImageLoaderFactory.setDefaultImageLoadHandler(handler);
        ImageLoaderFactory.setDefaultImageReSizer(DemoDuiTangImageReSizer.getInstance());
    }

    private void initRequestCache() {
        String dir = "request-cache";
        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
    }

}