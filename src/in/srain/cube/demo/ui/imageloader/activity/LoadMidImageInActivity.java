package in.srain.cube.demo.ui.imageloader.activity;

import android.os.Bundle;
import android.widget.GridView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseActivity;
import in.srain.cube.demo.datamodel.Images;
import in.srain.cube.demo.ui.viewholders.StringMiddleImageViewViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.views.list.ListViewDataAdapter;

public class LoadMidImageInActivity extends DemoTitleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHeaderTitle(R.string.cube_demo_load_mid_image_in_activity);
        setContentView(R.layout.load_middle_image);

        ImageLoader imageLoader = ImageLoaderFactory.create(this).tryToAttachToContainer(this);

        final GridView gridView = (GridView) findViewById(R.id.load_middle_image_grid_view);

        final ListViewDataAdapter<String> adapter = new ListViewDataAdapter<String>();
        adapter.setViewHolderClass(this, StringMiddleImageViewViewHolder.class, imageLoader);
        adapter.getDataList().addAll(Images.getImages());

        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
