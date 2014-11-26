package in.srain.cube.demo.ui.activity.imagelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.ImageView.ScaleType;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.request.CacheAbleRequest;
import in.srain.cube.request.JsonData;
import in.srain.cube.demo.R;
import in.srain.cube.mints.base.TitleBaseActivity;
import in.srain.cube.demo.data.DemoRequestData;
import in.srain.cube.demo.data.Images;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.list.ViewHolderCreator;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrRotateHeaderFrame;

public class GridListImageActivity extends TitleBaseActivity {

    private static final int sGirdImageSize = (LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(12 + 12 + 10)) / 2;
    private ImageLoader mImageLoader;

    private static final ImageReuseInfo sGridImageReuseInfo = Images.sImageReuseInfoManger.create("big_360");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageLoader = ImageLoaderFactory.create(this);
        setContentView(R.layout.activity_image_gird);
        final View v = mContentContainer;

        final GridViewWithHeaderAndFooter gridView = (GridViewWithHeaderAndFooter) v.findViewById(R.id.ly_image_list_grid);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View headerView = layoutInflater.inflate(R.layout.test_header_view, null);
        View footerView = layoutInflater.inflate(R.layout.test_footer_view, null);
        gridView.addHeaderView(headerView);
        gridView.addFooterView(footerView);

        final ListViewDataAdapter<JsonData> adapter = new ListViewDataAdapter<JsonData>(new ViewHolderCreator<JsonData>() {
            @Override
            public ViewHolderBase<JsonData> createViewHolder() {
                return new ViewHolder();
            }
        });
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        setHeaderTitle("GridViewWithHeaderAndFooter");

        final PtrRotateHeaderFrame ptrFrame = (PtrRotateHeaderFrame) v.findViewById(R.id.ly_ptr_frame);
        ptrFrame.setKeepHeaderWhenRefresh(true);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                DemoRequestData.getImageList(false, new DemoRequestData.ImageListDataHandler() {

                    public void onData(JsonData data, CacheAbleRequest.ResultType type, boolean outOfDate) {
                        String msg = String.format(
                                " onData\n result type: %s\n out of date: %s\n time: %s",
                                type, outOfDate, data.optJson("data").optString("time"));
                        Toast.makeText(GridListImageActivity.this, msg, 1).show();
                        adapter.getDataList().clear();
                        adapter.getDataList().addAll(data.optJson("data").optJson("list").toArrayList());
                        adapter.notifyDataSetChanged();
                        ptrFrame.refreshComplete();
                    }
                });
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }
        });
        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.doRefresh();
            }
        }, 150);
    }

    private class ViewHolder extends ViewHolderBase<JsonData> {

        private CubeImageView mImageView;

        @Override
        public View createView(LayoutInflater inflater) {
            View view = inflater.inflate(R.layout.item_image_list_grid, null);
            mImageView = (CubeImageView) view.findViewById(R.id.iv_item_iamge_list_grid);
            mImageView.setScaleType(ScaleType.CENTER_CROP);

            LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(sGirdImageSize, sGirdImageSize);
            mImageView.setLayoutParams(lyp);
            return view;
        }

        @Override
        public void showData(int position, JsonData itemData) {
            String url = itemData.optString("pic");
            mImageView.loadImage(mImageLoader, url, sGridImageReuseInfo);
        }
    }
}
