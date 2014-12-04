package in.srain.cube.demo.ui.imagelist.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import in.srain.cube.demo.R;
import in.srain.cube.demo.base.DemoTitleBaseActivity;
import in.srain.cube.demo.data.DemoRequestData;
import in.srain.cube.demo.data.Images;
import in.srain.cube.demo.ui.imagelist.LoadMiddleImageController;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.mints.base.TitleBaseActivity;
import in.srain.cube.request.CacheAbleRequest;
import in.srain.cube.request.JsonData;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.list.ViewHolderCreator;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

import java.util.Arrays;

public class LoadMidImageInActivity extends DemoTitleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHeaderTitle(R.string.cube_demo_load_mid_image_in_activity);
        setContentView(R.layout.load_middle_image);

        ImageLoader imageLoader = ImageLoaderFactory.create(this);

        final GridView gridView = (GridView) findViewById(R.id.load_middle_image_grid_view);
        new LoadMiddleImageController().takeControlDisplay(imageLoader, gridView);
    }
}
