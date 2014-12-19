package in.srain.cube.demo.ui.imageloader.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseFragment;
import in.srain.cube.demo.datamodel.Images;
import in.srain.cube.demo.ui.viewholders.StringMiddleImageViewViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.views.list.ListViewDataAdapter;

import java.util.Arrays;

public class LoadMidImageInFragment extends DemoTitleBaseFragment {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_load_middle_image_in_fragment);
        ImageLoader imageLoader = ImageLoaderFactory.create(getActivity()).attachToCubeFragment(this);

        final View v = inflater.inflate(R.layout.load_middle_image, container, false);
        final GridView gridView = (GridView) v.findViewById(R.id.load_middle_image_grid_view);

        final ListViewDataAdapter<String> adapter = new ListViewDataAdapter<String>();
        adapter.setViewHolderClass(this, StringMiddleImageViewViewHolder.class, imageLoader);
        adapter.getDataList().addAll(Arrays.asList(Images.imageUrls));

        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
    }
}