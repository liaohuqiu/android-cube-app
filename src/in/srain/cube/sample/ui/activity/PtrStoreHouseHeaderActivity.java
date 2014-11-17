package in.srain.cube.sample.ui.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.sample.R;
import in.srain.cube.sample.ui.activity.base.TitleBaseActivity;
import in.srain.cube.views.ptr.PtrBaseFrame;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.util.CLog;
import in.srain.cube.util.Debug;
import in.srain.cube.views.ptr.PtrUIHandler;

public class PtrStoreHouseHeaderActivity extends TitleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_house_ptr_header);

        setHeaderTitle("Storehouse");

        Resources res = getResources();
        TypedArray colors = res.obtainTypedArray(R.array.path);

        CubeImageView imageView = (CubeImageView) findViewById(R.id.store_house_ptr_image);
        ImageLoader imageLoader = ImageLoader.createDefault(this);
        String pic = "http://img5.duitang.com/uploads/item/201406/28/20140628122218_fLQyP.thumb.jpeg";
        imageView.loadImage(imageLoader, pic);

        Debug.DEBUG_PTR_FRAME = true;
        final PtrBaseFrame frame = (PtrBaseFrame) findViewById(R.id.store_house_ptr_frame);
        final StoreHouseHeader houseHeader = (StoreHouseHeader) findViewById(R.id.store_house_ptr_header);
        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrBaseFrame frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrBaseFrame frame) {
                CLog.d("ptr-test", "onRefreshBegin");
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 4000);
            }
        });
        frame.setPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIRefreshBegin() {
                houseHeader.beginLoading();
            }

            /**
             * when user release
             *
             * @param isLoading   is in refreshing
             * @param crossRefreshLine the position when released has crossing the refresh heigh
             */
            @Override
            public void onUIRelease(boolean isLoading, boolean crossRefreshLine) {

            }

            @Override
            public void onUIScrollBackToTop(boolean isLoading) {

            }

            @Override
            public void onUIRefreshComplete(boolean inUnderTouch) {
                CLog.d("ptr-test", "onRefreshComplete");
                houseHeader.loadFinish();
            }

            @Override
            public void onUIPositionChange(boolean isUnderTouch, boolean isLoading, int oldPosition, int currentPosition, float oldPercent, float currentPercent) {
                float f = currentPosition * 1f / houseHeader.getMeasuredHeight();
                if (f > 1) f = 1;
                // CLog.d("ptr-test", "onPositionChange: %s %s", currentPosition, houseHeader.getMeasuredHeight(), houseHeader.getHeight());
                houseHeader.setProgress(f);
                houseHeader.invalidate();
            }
        });
    }
}
