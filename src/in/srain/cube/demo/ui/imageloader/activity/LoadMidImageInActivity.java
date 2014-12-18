package in.srain.cube.demo.ui.imageloader.activity;

import android.os.Bundle;
import android.widget.GridView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseActivity;
import in.srain.cube.demo.ui.imageloader.LoadMiddleImageController;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;

public class LoadMidImageInActivity extends DemoTitleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHeaderTitle(R.string.cube_demo_load_mid_image_in_activity);
        setContentView(R.layout.load_middle_image);

        ImageLoader imageLoader = ImageLoaderFactory.create(this).tryToAttachToContainer(this);

        final GridView gridView = (GridView) findViewById(R.id.load_middle_image_grid_view);
        new LoadMiddleImageController().takeControlDisplay(imageLoader, gridView);
    }
}
