package in.srain.cube.demo.ui.imagelist.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseActivity;
import in.srain.cube.demo.ui.imagelist.LoadBigImageController;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;

public class LoadBigImageInActivity extends DemoTitleBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHeaderTitle(R.string.cube_demo_load_big_image_in_activity);
        setContentView(R.layout.load_big_image);

        ImageLoader imageLoader = ImageLoaderFactory.create(this);
        final View v = mContentContainer;
        ListView listView = (ListView) v.findViewById(R.id.load_big_image_list_view);
        new LoadBigImageController().takeControlDisplay(imageLoader, listView);
    }
}
