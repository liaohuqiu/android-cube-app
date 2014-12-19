package in.srain.cube.demo.ui.loadmore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import com.squareup.otto.Subscribe;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.data.ImageListData;
import in.srain.cube.demo.datamodel.ImageListDataModel;
import in.srain.cube.demo.event.EventBus;
import in.srain.cube.demo.event.SimpleEventHandler;
import in.srain.cube.demo.ui.viewholders.ImageListItemSmallImageViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.impl.DefaultImageLoadHandler;
import in.srain.cube.util.CLog;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.PagedListViewDataAdapter;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

import java.util.List;

public class LoadMoreListViewFragment extends DemoTitleBaseFragment {

    private PagedListViewDataAdapter<ImageListItem> mAdapter;
    private ImageListDataModel mDataModel;
    private ImageLoader mImageLoader;
    private PtrFrameLayout mPtrFrameLayout;
    private ListView mListView;

    @Override
    public View createView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_more_list_view);

        mImageLoader = ImageLoaderFactory.create(getContext()).attachToCubeFragment(this);
        ((DefaultImageLoadHandler) mImageLoader.getImageLoadHandler()).setImageRounded(true, 25);

        // set up data
        mDataModel = new ImageListDataModel();

        mAdapter = new PagedListViewDataAdapter<ImageListItem>();
        mAdapter.setViewHolderClass(this, ImageListItemSmallImageViewHolder.class, mImageLoader);
        mAdapter.setListPageInfo(mDataModel.getListPageInfo());

        // set up views
        final View view = inflater.inflate(R.layout.fragment_load_more_list_view, null);

        // pull to refresh
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.load_more_list_view_ptr_frame);
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
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mDataModel.queryFirstPage();
            }
        });

        // list view
        mListView = (ListView) view.findViewById(R.id.load_more_small_image_list_view);

        // header place holder
        View headerMarginView = new View(getContext());
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
        mListView.addHeaderView(headerMarginView);

        // load more container
        final LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultHeader();

        // binding view and data
        mListView.setAdapter(mAdapter);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
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
                loadMoreListViewContainer.loadMoreFinish(mDataModel.getListPageInfo().getPage(), mDataModel.getListPageInfo().hasMore());
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
