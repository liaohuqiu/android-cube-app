package in.srain.cube.demo.ui.activity.imagelist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.demo.R;
import in.srain.cube.mints.base.TitleBaseActivity;
import in.srain.cube.demo.data.Images;
import in.srain.cube.util.CLog;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.list.ViewHolderCreator;

import java.util.Arrays;

public class BigImageListActivity extends TitleBaseActivity {

    private static final int sBigImageSize = LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(10 + 10);

    private ImageLoader mImageLoader;
    private static final ImageReuseInfo sBigImageReuseInfo = Images.sImageReuseInfoManger.create("big");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHeaderTitle("Big Image");
        setContentView(R.layout.activity_image_list_big);
        mImageLoader = ImageLoaderFactory.create(this);

        final View v = mContentContainer;
        ListView listView = (ListView) v.findViewById(R.id.ly_image_list_big);
        ListViewDataAdapter<String> adapter = new ListViewDataAdapter<String>();
        listView.setAdapter(adapter);
        adapter.getDataList().addAll(Arrays.asList(Images.imageUrls));
        adapter.notifyDataSetChanged();
    }

    private static class ViewHolder1 extends ViewHolderBase<String> {

        /**
         * create a view from resource Xml file, and hold the view that may be used in displaying data.
         *
         * @param layoutInflater
         */
        @Override
        public View createView(LayoutInflater layoutInflater) {
            return null;
        }

        /**
         * using the held views to display data
         *
         * @param position
         * @param itemData
         */
        @Override
        public void showData(int position, String itemData) {

        }
    }

    private class ViewHolder extends ViewHolderBase<String> {

        private CubeImageView mImageView;

        @Override
        public View createView(LayoutInflater inflater) {
            View view = inflater.inflate(R.layout.item_image_list_big, null);
            mImageView = (CubeImageView) view.findViewById(R.id.iv_item_image_list_big);
            mImageView.setScaleType(ScaleType.CENTER_CROP);

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
