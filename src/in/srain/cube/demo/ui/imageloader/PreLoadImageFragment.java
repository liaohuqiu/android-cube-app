package in.srain.cube.demo.ui.imageloader;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import in.srain.cube.demo.R;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageTask;
import in.srain.cube.image.iface.ImageLoadHandler;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.util.CLog;
import in.srain.cube.util.Debug;

public class PreLoadImageFragment extends TitleBaseFragment {

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeaderTitle(R.string.cube_demo_pre_load_image);
        View view = inflater.inflate(R.layout.fragment_pre_load_image, null);

        final CubeImageView imageView = (CubeImageView) view.findViewById(R.id.pre_load_image_view);

        ImageLoadHandler imageLoadHandler = new ImageLoadHandler() {

            @Override
            public void onLoading(ImageTask imageTask, CubeImageView cubeImageView) {

            }

            @Override
            public void onLoadFinish(ImageTask imageTask, CubeImageView cubeImageView, BitmapDrawable drawable) {

                if (cubeImageView != null && drawable != null) {
                    cubeImageView.setImageDrawable(drawable);
                }
            }

            @Override
            public void onLoadError(ImageTask imageTask, CubeImageView imageView, int errorCode) {

            }
        };
        final ImageLoader imageLoader = ImageLoaderFactory.create(getContext());


        final ImageLoader preLoadImageLoader = ImageLoaderFactory.create(getContext());
        ImageLoadHandler preLoadHandler = new ImageLoadHandler() {
            @Override
            public void onLoading(ImageTask imageTask, CubeImageView cubeImageView) {
                CLog.d(Debug.DEBUG_IMAGE_LOG_TAG, "onLoading: %s", imageTask.getRemoteUrl());
            }

            @Override
            public void onLoadFinish(ImageTask imageTask, CubeImageView cubeImageView, BitmapDrawable drawable) {
                CLog.d(Debug.DEBUG_IMAGE_LOG_TAG, "onLoadFinish: %s", imageTask.getRemoteUrl());
                // imageView.loadImage(imageLoader, imageTask.getRemoteUrl());

                // save to disk
                preLoadImageLoader.flushFileCache();
            }

            @Override
            public void onLoadError(ImageTask imageTask, CubeImageView imageView, int errorCode) {
                CLog.e(Debug.DEBUG_IMAGE_LOG_TAG, "onLoadError: %s", imageTask.getRemoteUrl());
            }
        };
        preLoadImageLoader.setImageLoadHandler(preLoadHandler);
        String[] urls = new String[]{
                "http://cdn.duitang.com/uploads/item/201408/07/20140807224553_VXaUc.thumb.jpeg",
        };
        urls = new String[]{"http://static.kuaihuala.com/uploads/share/20141223/14193276354943.jpg",
                "http://static.kuaihuala.com/uploads/adver/20141222/14192466504022.jpg",
                "http://static.kuaihuala.com/uploads/adver/20141222/14192464894056.jpg",
                "http://static.kuaihuala.com/uploads/download/20141230/14199214419877.jpg",
                "http://static.kuaihuala.com/uploads/download/20141222/14192471472179.jpg",
                "http://static.kuaihuala.com/uploads/download/20141222/1419245961720.jpg",
                "http://static.kuaihuala.com/uploads/share/20141229/14198335778831.jpg",
                "http://static.kuaihuala.com/uploads/share/20141223/14193308734597.jpg",
                "http://static.kuaihuala.com/uploads/download/20141229/14198460398571.jpg",
                "http://static.kuaihuala.com/uploads/download/20141229/14198399966853.jpg",
                "http://static.kuaihuala.com/uploads/download/20141229/14198498909027.jpg",
                "http://static.kuaihuala.com/uploads/adver/20141230/1419924451817.jpg",
                "http://static.kuaihuala.com/uploads/download/20141231/14199924451997.jpg",
                "http://static.kuaihuala.com/uploads/download/20141231/1419993178509.jpg",
                "http://static.kuaihuala.com/uploads/download/20141231/14199938863183.jpg",
                "http://static.kuaihuala.com/uploads/download/20141231/14199956659657.jpg",
                "http://static.kuaihuala.com/uploads/download/20141231/14199960857576.jpg",
                "http://static.kuaihuala.com/uploads/download/20141231/14199965043272.jpg"};
        preLoadImageLoader.preLoadImages(urls);
        return view;
    }
}