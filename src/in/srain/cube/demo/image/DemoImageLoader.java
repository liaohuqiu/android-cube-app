package in.srain.cube.demo.image;

import android.content.Context;
import in.srain.cube.app.lifecycle.LifeCycleComponent;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageProvider;
import in.srain.cube.image.iface.ImageLoadHandler;
import in.srain.cube.image.iface.ImageReSizer;
import in.srain.cube.image.iface.ImageTaskExecutor;

public class DemoImageLoader extends ImageLoader implements LifeCycleComponent {

    public static ImageLoader createStableImageLoader(Context context) {
        return null;
    }

    public DemoImageLoader(Context context, ImageProvider imageProvider, ImageTaskExecutor imageTaskExecutor, ImageReSizer imageReSizer, ImageLoadHandler imageLoadHandler) {
        super(context, imageProvider, imageTaskExecutor, imageReSizer, imageLoadHandler);
    }

    @Override
    public void onBecomesVisibleFromTotallyInvisible() {
        recoverWork();
    }

    @Override
    public void onBecomesPartiallyInvisible() {
        pauseWork();
    }

    @Override
    public void onBecomesVisible() {
        resumeWork();
    }

    @Override
    public void onBecomesTotallyInvisible() {
        stopWork();
    }

    @Override
    public void onDestroy() {
        destroy();
    }
}
