package in.srain.cube.demo.ui.loadmore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.datamodel.ImageListDataModel;
import in.srain.cube.demo.event.DemoSimpleEventHandler;
import in.srain.cube.demo.event.ErrorMessageDataEvent;
import in.srain.cube.demo.event.EventCenter;
import in.srain.cube.demo.event.ImageListDataEvent;
import in.srain.cube.demo.ui.viewholders.ImageListItemSmallImageViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.PagedListViewDataAdapter;
import in.srain.cube.views.loadmore.CubeScrollView;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.loadmore.LoadMoreScrollViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class LoadMoreScrollViewFragment extends DemoTitleBaseFragment {

    private ImageListDataModel mDataModel;
    private ImageLoader mImageLoader;
    private PtrFrameLayout mPtrFrameLayout;
    private CubeScrollView mCubeScrollView;

    @Override
    public View createView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_more_scroll_view);

        mImageLoader = ImageLoaderFactory.create(getContext()).attachToCubeFragment(this);

        // set up data
        mDataModel = new ImageListDataModel(5);


        // set up views
        final View view = inflater.inflate(R.layout.fragment_load_more_scroll_view, null);

        // pull to refresh
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.load_more_list_view_ptr_frame);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mCubeScrollView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mDataModel.queryFirstPage();
            }
        });

        // list view
        mCubeScrollView = (CubeScrollView) view.findViewById(R.id.load_more_small_image_scroll_view);
        for (int i = 0; i < 25; i++) {
        	Button button = new Button(getActivity());
        	button.setText(""+i);
        	((ViewGroup)mCubeScrollView.getChildAt(0)).addView(button);
			
		}

        // header place holder
        View headerMarginView = new View(getContext());
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));

        // load more container
        final LoadMoreScrollViewContainer loadMoreListViewContainer = (LoadMoreScrollViewContainer) view.findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultHeader();

        // binding view and data
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                mDataModel.queryNextPage();
            }
        });

        // process data
        EventCenter.bindContainerAndHandler(this, new DemoSimpleEventHandler() {

            public void onEvent(ImageListDataEvent event) {

                // ptr
                mPtrFrameLayout.refreshComplete();

                // load more
                Button button = new Button(getActivity());
                loadMoreListViewContainer.loadMoreFinish(mDataModel.getListPageInfo().isEmpty(), mDataModel.getListPageInfo().hasMore());
                button.setText(((ViewGroup)mCubeScrollView.getChildAt(0)).getChildCount()-1+"");
                ((ViewGroup)mCubeScrollView.getChildAt(0)).addView(button,((ViewGroup)mCubeScrollView.getChildAt(0)).getChildCount()-1);
            }

            public void onEvent(ErrorMessageDataEvent event) {
                loadMoreListViewContainer.loadMoreError(0, event.message);
            }

        }).tryToRegisterIfNot();

        // auto load data
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 150);

        return view;
    }
}
