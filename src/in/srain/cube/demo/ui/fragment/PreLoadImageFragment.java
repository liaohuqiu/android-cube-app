package in.srain.cube.demo.ui.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.data.DemoRequestData;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageTask;
import in.srain.cube.image.iface.ImageLoadHandler;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.request.JsonData;
import in.srain.cube.request.RequestJsonHandler;
import in.srain.cube.util.CLog;
import in.srain.cube.util.Debug;

public class PreLoadImageFragment extends TitleBaseFragment {

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeaderTitle(R.string.cube_demo_block_pre_load_image);
        View view = inflater.inflate(R.layout.fragment_pre_load_image, null);

        final ImageLoader imageLoader = ImageLoaderFactory.create(getContext());
        ImageLoadHandler imageLoadHandler = new ImageLoadHandler() {
            @Override
            public void onLoading(ImageTask imageTask, CubeImageView cubeImageView) {
                CLog.d(Debug.DEBUG_IMAGE_LOG_TAG, "onLoading: %s", imageTask.getRemoteUrl());
            }

            @Override
            public void onLoadFinish(ImageTask imageTask, CubeImageView cubeImageView, BitmapDrawable drawable) {
                CLog.d(Debug.DEBUG_IMAGE_LOG_TAG, "onLoadFinish: %s", imageTask.getRemoteUrl());
                imageLoader.flushFileCache();
            }

            @Override
            public void onLoadError(ImageTask imageTask, CubeImageView imageView, int errorCode) {
                CLog.d(Debug.DEBUG_IMAGE_LOG_TAG, "onLoadError: %s", imageTask.getRemoteUrl());
            }
        };
        imageLoader.setImageLoadHandler(imageLoadHandler);
        String[] urls = new String[]{
                "http://cdn.duitang.com/uploads/item/201408/07/20140807224553_VXaUc.thumb.jpeg",
                "http://121.40.210.61/uploads/adver/20141113/14158502795574.png"
        };
        imageLoader.attachToCubeFragment(this);
        imageLoader.preLoadImages(urls);
        return view;
    }
}