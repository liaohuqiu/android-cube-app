package in.srain.cube.demo.ui.imageloader;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.data.Images;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;

import java.util.Arrays;

public class LoadSmallImageController {

    private static final ImageReuseInfo sSmallImageReuseInfo = Images.sImageReuseInfoManger.create("small_180");
    private ImageLoader mImageLoader;

    public void takeControlDisplay(ImageLoader imageLoader, ListView listView) {
        mImageLoader = imageLoader;

        ListViewDataAdapter<String> adapter = new ListViewDataAdapter<String>();
        adapter.setViewHolderClass(this, ViewHolder.class);
        listView.setAdapter(adapter);
        adapter.getDataList().addAll(Arrays.asList(Images.imageUrls));
        adapter.notifyDataSetChanged();
    }

    private class ViewHolder extends ViewHolderBase<String> {

        private CubeImageView mImageView;

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
