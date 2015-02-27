package in.srain.cube.demo.ui.loadmore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.datamodel.ImageListDataModel;
import in.srain.cube.demo.event.DemoSimpleEventHandler;
import in.srain.cube.demo.event.EventCenter;
import in.srain.cube.demo.event.ImageListDataEvent;
import in.srain.cube.demo.ui.viewholders.ImageListItemSmallImageViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.PagedListViewDataAdapter;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

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

        // set up data
        mDataModel = new ImageListDataModel(5);

        mAdapter = new PagedListViewDataAdapter<ImageListItem>();
        mAdapter.setViewHolderClass(this, ImageListItemSmallImageViewHolder.class, mImageLoader);
        mAdapter.setListPageInfo(mDataModel.getListPageInfo());

        // set up views
        final View view = inflater.inflate(R.layout.fragment_load_more_list_view, null);

        // pull to refresh
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.load_more_list_view_ptr_frame);

        mPtrFrameLayout.setLoadingMinTime(1000);
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

        EventCenter.bindContainerAndHandler(this, new DemoSimpleEventHandler() {

            public void onEvent(ImageListDataEvent event) {
                mPtrFrameLayout.refreshComplete();
            }

        }).tryToRegisterIfNot();

        mPtrFrameLayout.addPtrUIHandler(new PtrUIRefreshCompleteHandler() {
            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                loadMoreListViewContainer.loadMoreFinish(mDataModel.getListPageInfo().isEmpty(), mDataModel.getListPageInfo().hasMore());
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
