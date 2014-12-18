package in.srain.cube.demo.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import in.srain.cube.demo.R;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;

public class EnclosingInnerClassFragment extends ViewHolderDemoBaseFragment {

    protected void setupAdapter(ListViewDataAdapter<String> adapter) {
        adapter.setViewHolderClass(this, ClassInnerInstanceViewHolder.class, mImageLoader);
    }

    private class ClassInnerInstanceViewHolder extends ViewHolderBase<String> {

        private ImageLoader mImageLoader;
        private CubeImageView mImageView;

        public ClassInnerInstanceViewHolder(ImageLoader imageLoader) {
            mImageLoader = imageLoader;
        }

        @Override
        public View createView(LayoutInflater inflater) {
            View v = inflater.inflate(R.layout.load_small_image_list_item, null);
            mImageView = (CubeImageView) v.findViewById(R.id.load_small_image_list_item_image_view);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return v;
        }

        @Override
        public void showData(int position, String itemData) {
            mImageView.loadImage(mImageLoader, itemData, sSmallImageReuseInfo);
        }
    }
}
