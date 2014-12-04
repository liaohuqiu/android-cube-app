package in.srain.cube.demo.ui.imagelist.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.ui.imagelist.LoadMiddleImageController;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;

public class LoadMidImageInFragment extends DemoTitleBaseFragment {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_middle_image_in_fragment);
        ImageLoader imageLoader = ImageLoaderFactory.create(getActivity()).attachToCubeFragment(this);

        final View v = inflater.inflate(R.layout.load_middle_image, container, false);
        final GridView gridListView = (GridView) v.findViewById(R.id.load_middle_image_grid_view);

        new LoadMiddleImageController().takeControlDisplay(imageLoader, gridListView);

        return v;
    }
}