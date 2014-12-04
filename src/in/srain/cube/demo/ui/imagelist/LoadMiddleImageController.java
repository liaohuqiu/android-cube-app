package in.srain.cube.demo.ui.imagelist;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
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

public class LoadMiddleImageController {

    private static final int sGirdImageSize = (LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(12 + 12 + 10)) / 2;
    private static final ImageReuseInfo sGridImageReuseInfo = Images.sImageReuseInfoManger.create("big_360");

    private ImageLoader mImageLoader;

    public void takeControlDisplay(ImageLoader imageLoader, GridView gridView) {
        mImageLoader = imageLoader;
        gridView.setNumColumns(2);
        final ListViewDataAdapter<String> adapter = new ListViewDataAdapter<String>();
        adapter.setViewHolderClass(this, ViewHolder.class);
        adapter.getDataList().addAll(Arrays.asList(Images.imageUrls));

        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private class ViewHolder extends ViewHolderBase<String> {

        private CubeImageView mImageView;

        @Override
        public View createView(LayoutInflater inflater) {
            View view = inflater.inflate(R.layout.load_middle_image_list_item, null);
            mImageView = (CubeImageView) view.findViewById(R.id.load_middle_image_list_image_view);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(sGirdImageSize, sGirdImageSize);
            mImageView.setLayoutParams(lyp);
            return view;
        }

        @Override
        public void showData(int position, String itemData) {
            mImageView.loadImage(mImageLoader, itemData, sGridImageReuseInfo);
        }
    }
}
