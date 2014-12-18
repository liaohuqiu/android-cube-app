package in.srain.cube.demo.ui.imageloader;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.data.Images;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;

import java.util.Arrays;

public class LoadBigImageController {

    private static final int sBigImageSize = LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(10 + 10);
    private static final ImageReuseInfo sBigImageReuseInfo = Images.sImageReuseInfoManger.create("big");
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
            View view = inflater.inflate(R.layout.load_big_image_list_item, null);
            mImageView = (CubeImageView) view.findViewById(R.id.load_big_image_list_item_image_view);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(sBigImageSize, sBigImageSize);
            mImageView.setLayoutParams(lyp);

            return view;
        }

        @Override
        public void showData(int position, String itemData) {
            mImageView.loadImage(mImageLoader, itemData, sBigImageReuseInfo);
        }
    }
}
