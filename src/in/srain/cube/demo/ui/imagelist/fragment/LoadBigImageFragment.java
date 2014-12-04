package in.srain.cube.demo.ui.imagelist.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.ui.imagelist.LoadBigImageController;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.mints.base.TitleBaseFragment;

public class LoadBigImageFragment extends DemoTitleBaseFragment {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_big_image_in_fragment);

        final View view = inflater.inflate(R.layout.load_big_image, null);
        ListView listView = (ListView) view.findViewById(R.id.load_big_image_list_view);

        ImageLoader imageLoader = ImageLoaderFactory.create(getActivity());
        new LoadBigImageController().takeControlDisplay(imageLoader, listView);
        return view;
    }
}
