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
import in.srain.cube.image.impl.DefaultImageLoadHandler;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.util.LocalDisplay;

public class RoundedImageFragment extends TitleBaseFragment {

    private CubeImageView mDefaultImageView;
    private CubeImageView mRoundedImageView;
    private String mUrl = "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg";

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeaderTitle(R.string.cube_demo_pre_load_image);
        View view = inflater.inflate(R.layout.fragment_image_rounded_image, null);

        mDefaultImageView = (CubeImageView) view.findViewById(R.id.image_rounded_image_view1);
        mRoundedImageView = (CubeImageView) view.findViewById(R.id.image_rounded_image_view2);


        DefaultImageLoadHandler defaultImageLoadHandler = new DefaultImageLoadHandler(getContext()) {
            @Override
            public void onLoadFinish(ImageTask imageTask, CubeImageView imageView, BitmapDrawable drawable) {
                super.onLoadFinish(imageTask, imageView, drawable);
                loadRoundedImage();
            }
        };
        ImageLoader imageLoaderDefault = ImageLoaderFactory.create(getContext(), defaultImageLoadHandler);
        mDefaultImageView.loadImage(imageLoaderDefault, mUrl);
        return view;
    }

    private void loadRoundedImage() {

        DefaultImageLoadHandler defaultImageLoadHandler = new DefaultImageLoadHandler(getContext());
        defaultImageLoadHandler.setImageRounded(true, LocalDisplay.dp2px(50));
        ImageLoader roundedImageLoader = ImageLoaderFactory.create(getContext(), defaultImageLoadHandler);
        mRoundedImageView.loadImage(roundedImageLoader, mUrl);
    }
}