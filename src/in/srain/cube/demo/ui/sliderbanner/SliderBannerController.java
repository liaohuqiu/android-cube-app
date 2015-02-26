package in.srain.cube.demo.ui.sliderbanner;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import in.srain.cube.demo.R;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.JsonData;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.DotView;
import in.srain.cube.views.banner.BannerAdapter;
import in.srain.cube.views.banner.SliderBanner;

public class SliderBannerController {

    public final static int Height = (int) (LocalDisplay.SCREEN_WIDTH_PIXELS * 1f / 16 * 9.6f);
    private SliderBanner mSliderBanner;
    private ImageLoader mImageLoader;
    private InnerAdapter mBannerAdapter = new InnerAdapter();
    private DotView mDotView;

    private View.OnClickListener mClickItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }
    };

    public SliderBannerController(SliderBanner sliderBanner) {

        mDotView = (DotView) sliderBanner.findViewById(R.id.demo_slider_banner_indicator);

        mImageLoader = ImageLoaderFactory.createStableImageLoader(sliderBanner.getContext());
        mImageLoader.tryToAttachToContainer(sliderBanner.getContext());
        mSliderBanner = sliderBanner;

        sliderBanner.setAdapter(mBannerAdapter);
    }

    public void play(JsonData list) {
        mBannerAdapter.setData(list);
        mBannerAdapter.notifyDataSetChanged();
        mSliderBanner.setDotNum(list.length());
        mSliderBanner.beginPlay();
    }

    private class InnerAdapter extends BannerAdapter {
        private JsonData mDataList;

        public void setData(JsonData data) {
            mDataList = data;
        }

        public JsonData getItem(int position) {
            if (mDataList == null)
                return null;
            return mDataList.optJson(getPositionForIndicator(position));
        }

        @Override
        public int getPositionForIndicator(int position) {
            if (null == mDataList || mDataList.length() == 0) {
                return 0;
            }
            return position % mDataList.length();
        }

        @Override
        public View getView(LayoutInflater layoutInflater, int position) {
            View convertView = layoutInflater.inflate(R.layout.demo_slider_banner_item, null);

            JsonData item = getItem(position);
            CubeImageView imageView = (CubeImageView) convertView.findViewById(R.id.demo_banner_item_image);
            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            String pic = item.optString("pic");
            imageView.loadImage(mImageLoader, pic);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.demo_banner_item_title);
            titleTextView.setText(item.optString("title"));

            convertView.setTag(item);
            convertView.setOnClickListener(mClickItemListener);
            return convertView;
        }

        @Override
        public int getCount() {
            if (mDataList == null) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }
    }
}
