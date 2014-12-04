package in.srain.cube.demo.ui.imagelist.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.ui.imagelist.LoadSmallImageController;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.impl.DefaultImageLoadHandler;

public class LoadSmallImageInFragment extends DemoTitleBaseFragment {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_small_image_in_fragment);

        ImageLoader imageLoader = ImageLoaderFactory.create(getActivity());
        ((DefaultImageLoadHandler) imageLoader.getImageLoadHandler()).setImageRounded(true, 25);

        final View v = inflater.inflate(R.layout.load_small_image, null);

        ListView listView = (ListView) v.findViewById(R.id.load_small_image_list_view);
        new LoadSmallImageController().takeControlDisplay(imageLoader, listView);
        return v;
    }
}
