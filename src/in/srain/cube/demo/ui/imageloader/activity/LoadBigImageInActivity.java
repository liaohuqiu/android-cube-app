package in.srain.cube.demo.ui.imageloader.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseActivity;
import in.srain.cube.demo.datamodel.Images;
import in.srain.cube.demo.ui.viewholders.StringBigImageViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.views.list.ListViewDataAdapter;

import java.util.Arrays;

public class LoadBigImageInActivity extends DemoTitleBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHeaderTitle(R.string.cube_demo_load_big_image_in_activity);
        setContentView(R.layout.load_big_image);

        ImageLoader imageLoader = ImageLoaderFactory.create(this).tryToAttachToContainer(this);

        final View v = mContentContainer;
        ListViewDataAdapter<String> adapter = new ListViewDataAdapter<String>();
        adapter.setViewHolderClass(this, StringBigImageViewHolder.class, imageLoader);
        adapter.getDataList().addAll(Arrays.asList(Images.imageUrls));

        ListView listView = (ListView) v.findViewById(R.id.load_big_image_list_view);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
