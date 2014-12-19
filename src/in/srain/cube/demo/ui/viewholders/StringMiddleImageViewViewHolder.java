package in.srain.cube.demo.ui.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import in.srain.cube.demo.R;
import in.srain.cube.demo.datamodel.Images;
import in.srain.cube.demo.ui.imageloader.ImageSize;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.ViewHolderBase;

public class StringMiddleImageViewViewHolder extends ViewHolderBase<String> {

    private ImageLoader mImageLoader;
    private CubeImageView mImageView;

    public StringMiddleImageViewViewHolder(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    @Override
    public View createView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.load_middle_image_list_item, null);
        mImageView = (CubeImageView) view.findViewById(R.id.load_middle_image_list_image_view);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(ImageSize.sGirdImageSize, ImageSize.sGirdImageSize);
        mImageView.setLayoutParams(lyp);
        return view;
    }

    @Override
    public void showData(int position, String itemData) {
        mImageView.loadImage(mImageLoader, itemData, ImageSize.sGridImageReuseInfo);
    }
}
