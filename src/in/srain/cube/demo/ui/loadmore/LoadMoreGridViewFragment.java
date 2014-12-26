package in.srain.cube.demo.ui.loadmore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.squareup.otto.Subscribe;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.data.ImageListData;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.datamodel.ImageListDataModel;
import in.srain.cube.demo.event.EventBus;
import in.srain.cube.demo.event.SimpleEventHandler;
import in.srain.cube.demo.ui.viewholders.ImageListItemMiddleImageViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import in.srain.cube.views.list.PagedListViewDataAdapter;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

public class LoadMoreGridViewFragment extends DemoTitleBaseFragment {

    private PagedListViewDataAdapter<ImageListItem> mAdapter;
    private ImageListDataModel mDataModel;
    private ImageLoader mImageLoader;
    private PtrFrameLayout mPtrFrameLayout;
    private GridViewWithHeaderAndFooter mGridView;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_more_grid_view);

        mImageLoader = ImageLoaderFactory.create(getContext()).attachToCubeFragment(this);

        // set up data
        mDataModel = new ImageListDataModel();

        mAdapter = new PagedListViewDataAdapter<ImageListItem>();
        mAdapter.setViewHolderClass(this, ImageListItemMiddleImageViewHolder.class, mImageLoader);
        mAdapter.setListPageInfo(mDataModel.getListPageInfo());

        // set up views
        final View view = inflater.inflate(R.layout.fragment_load_more_grid_view, null);
        // pull to refresh
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.load_more_grid_view_ptr_frame);
        MaterialHeader ptrHeader = new MaterialHeader(getContext());
        PtrFrameLayout.LayoutParams lp = new PtrFrameLayout.LayoutParams(-1, -2);
        ptrHeader.setLayoutParams(lp);
        ptrHeader.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(15));
        ptrHeader.setPtrFrameLayout(mPtrFrameLayout);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setHeaderView(ptrHeader);
        mPtrFrameLayout.addPtrUIHandler(ptrHeader);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mGridView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mDataModel.queryFirstPage();
            }
        });

        mGridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.load_more_grid_view);

        // header place holder
        View headerMarginView = new View(getContext());
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
        mGridView.addHeaderView(headerMarginView);

        // load more container
        final LoadMoreGridViewContainer loadMoreContainer = (LoadMoreGridViewContainer) view.findViewById(R.id.load_more_grid_view_container);
        loadMoreContainer.setAutoLoadMore(false);
        loadMoreContainer.useDefaultHeader();

        // binding view and data
        mGridView.setAdapter(mAdapter);
        loadMoreContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                mDataModel.queryNextPage();
            }
        });

        EventBus.bindContainerAndHandler(this, new SimpleEventHandler() {

            @Subscribe
            public void onImageListDataEvent(ImageListData event) {
                mPtrFrameLayout.refreshComplete();
            }
        }).tryToRegisterIfNot();
        mPtrFrameLayout.addPtrUIHandler(new PtrUIRefreshCompleteHandler() {
            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                loadMoreContainer.loadMoreFinish(mDataModel.getListPageInfo().getPage(), mDataModel.getListPageInfo().hasMore());
                mAdapter.notifyDataSetChanged();
            }
        });

        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 150);

        return view;
    }

}
