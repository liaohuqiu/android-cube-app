package in.srain.cube.demo.ui.imageloader;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.fragment.DemoBlockMenuFragment;
import in.srain.cube.demo.ui.imageloader.activity.LoadBigImageInActivity;
import in.srain.cube.demo.ui.imageloader.activity.LoadMidImageInActivity;
import in.srain.cube.demo.ui.imageloader.activity.LoadSmallImageInActivity;
import in.srain.cube.demo.ui.imageloader.fragment.LoadBigImageFragment;
import in.srain.cube.demo.ui.imageloader.fragment.LoadMidImageInFragment;
import in.srain.cube.demo.ui.imageloader.fragment.LoadSmallImageInFragment;

import java.util.ArrayList;

public class ImageLoaderHomeFragment extends DemoBlockMenuFragment {

    @Override
    protected void addItemInfo(ArrayList<MenuItemInfo> itemInfos) {
        itemInfos.add(newItemInfo(R.string.cube_demo_load_big_image_in_activity, "#4d90fe", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), LoadBigImageInActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_mid_image_in_activity, "#4d90fe", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), LoadMidImageInActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_small_image_in_activity, "#4d90fe", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), LoadSmallImageInActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_big_image_in_fragment, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LoadBigImageFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_load_middle_image_in_fragment, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LoadMidImageInFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_load_small_image_in_fragment, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LoadSmallImageInFragment.class, null);
            }
        }));

        // pre load
        itemInfos.add(newItemInfo(R.string.cube_demo_pre_load_image, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(PreLoadImageFragment.class, null);
            }
        }));

        // image loader management
        itemInfos.add(newItemInfo(R.string.cube_demo_image_loader_management, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ImageLoaderManagementFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_customized_image_load_handler, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ImageLoaderManagementFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_rounded_image, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RoundedImageFragment.class, null);
            }
        }));
    }

    @Override
    protected void setupViews(View view) {
        setHeaderTitle(R.string.cube_demo_block_image_loader);
        super.setupViews(view);
    }
}
