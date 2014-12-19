package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import in.srain.cube.demo.R;
import in.srain.cube.demo.datamodel.DemoRequestData;
import in.srain.cube.demo.datamodel.Images;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.request.CacheAbleRequest;
import in.srain.cube.request.JsonData;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.list.ViewHolderCreator;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class GridViewWithHeaderAndFooterFragment extends TitleBaseFragment {

    private static final int sGirdImageSize = (LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(12 + 12 + 10)) / 2;
    private static final ImageReuseInfo sGridImageReuseInfo = Images.sImageReuseInfoManger.create("big_360");
    private ImageLoader mImageLoader;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_big_image_in_activity);

        mImageLoader = ImageLoaderFactory.create(getContext());
        View view = inflater.inflate(R.layout.fragment_grid_view_with_header_and_footer, null);

        final GridViewWithHeaderAndFooter gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.ly_image_list_grid);

        View headerView = inflater.inflate(R.layout.grid_view_header, null);
        View footerView = inflater.inflate(R.layout.grid_view_footer, null);
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

        final PtrClassicFrameLayout ptrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ly_image_list_grid);
        ptrFrame.setKeepHeaderWhenRefresh(true);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                DemoRequestData.getImageList(false, new DemoRequestData.ImageListDataHandler() {

                    public void onData(JsonData data, CacheAbleRequest.ResultType type, boolean outOfDate) {
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
                ptrFrame.autoRefresh();
            }
        }, 150);
        return view;
    }

    private class ViewHolder extends ViewHolderBase<JsonData> {

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
        public void showData(int position, JsonData itemData) {
            String url = itemData.optString("pic");
            mImageView.loadImage(mImageLoader, url, sGridImageReuseInfo);
        }
    }
}

