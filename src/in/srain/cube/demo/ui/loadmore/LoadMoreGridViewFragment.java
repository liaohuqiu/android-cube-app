package in.srain.cube.demo.ui.loadmore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import com.squareup.otto.Subscribe;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.datamodel.ImageListDataModel;
import in.srain.cube.demo.event.EventBus;
import in.srain.cube.demo.event.SimpleEventHandler;
import in.srain.cube.demo.ui.imageloader.LoadSmallImageController;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.impl.DefaultImageLoadHandler;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.PagedListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;

public class LoadMoreGridViewFragment extends DemoTitleBaseFragment {

    private PagedListViewDataAdapter<ImageListItem> mAdapter;
    private ImageListDataModel mDataModel;
    private ImageLoader mImageLoader;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mImageLoader = ImageLoaderFactory.create(getContext()).attachToCubeFragment(this);
        ((DefaultImageLoadHandler) mImageLoader.getImageLoadHandler()).setImageRounded(true, 25);

        // set up data
        mDataModel = new ImageListDataModel();

        mAdapter = new PagedListViewDataAdapter<ImageListItem>();
        mAdapter.setViewHolderClass(this, ViewHolder.class, mImageLoader);
        mAdapter.setListPageInfo(mDataModel.getListPageInfo());

        // set up views
        setHeaderTitle(R.string.cube_demo_load_more_list_view);

        final View view = inflater.inflate(R.layout.load_more_small_image, null);

        ListView listView = (ListView) view.findViewById(R.id.load_more_small_image_list_view);

        // header place holder
        View headerMarginView = new View(getContext());
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
        listView.addHeaderView(headerMarginView);

        // load more container
        final LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultHeader();

        // binding view and data
        listView.setAdapter(mAdapter);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                mDataModel.queryNextPage();
            }
        });

        EventBus.bindContainerAndHandler(this, new SimpleEventHandler() {

            @Subscribe
            public void onImageListDataEvent(ImageListDataModel.ImageListDataEvent event) {
                loadMoreListViewContainer.loadMoreFinish(mDataModel.getListPageInfo().getPage(), mDataModel.getListPageInfo().hasMore());
                mAdapter.notifyDataSetChanged();
            }
        }).tryToRegisterIfNot();

        mDataModel.queryFirstPage();

        return view;
    }

    class ViewHolder extends ViewHolderBase<ImageListItem> {

        private ImageLoader mImageLoader;
        private CubeImageView mImageView;

        private ViewHolder() {
            throw new RuntimeException("Call TopLevelViewHolder(ImageLoader imageLoader) instead.");
        }

        public ViewHolder(ImageLoader imageLoader) {
            mImageLoader = imageLoader;
        }

        @Override
        public View createView(LayoutInflater inflater) {
            View v = inflater.inflate(R.layout.load_small_image_list_item, null);
            mImageView = (CubeImageView) v.findViewById(R.id.load_small_image_list_item_image_view);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return v;
        }

        @Override
        public void showData(int position, ImageListItem itemData) {
            mImageView.loadImage(mImageLoader, itemData.picUrl, LoadSmallImageController.sSmallImageReuseInfo);
        }
    }

}
