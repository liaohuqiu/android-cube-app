package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.impl.DefaultImageLoadHandler;
import in.srain.cube.util.CLog;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;

public class LoadMoreListFragment extends DemoTitleBaseFragment {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_more_list_view);

        ImageLoader imageLoader = ImageLoaderFactory.create(getActivity()).attachToCubeFragment(this);
        ((DefaultImageLoadHandler) imageLoader.getImageLoadHandler()).setImageRounded(true, 25);
        ((DefaultImageLoadHandler) imageLoader.getImageLoadHandler()).setImageFadeIn(false);

        final View view = inflater.inflate(R.layout.load_more_small_image, null);
        ListView listView = (ListView) view.findViewById(R.id.load_more_small_image_list_view);
        LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);

        return view;
    }
}
