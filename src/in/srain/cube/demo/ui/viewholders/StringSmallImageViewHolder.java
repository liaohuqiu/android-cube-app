package in.srain.cube.demo.ui.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.imageloader.ImageSize;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.views.list.ViewHolderBase;

public class StringSmallImageViewHolder extends ViewHolderBase<String> {

    private CubeImageView mImageView;

    private ImageLoader mImageLoader;

    public StringSmallImageViewHolder(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    @Override
    public View createView(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.load_small_image_list_item, null);
        mImageView = (CubeImageView) v.findViewById(R.id.load_small_image_list_item_image_view);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return v;
    }

    @Override
    public void showData(int position, String itemData) {
        mImageView.loadImage(mImageLoader, itemData, ImageSize.sSmallImageReuseInfo);
    }
}
