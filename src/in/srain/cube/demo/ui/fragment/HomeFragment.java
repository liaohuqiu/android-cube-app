package in.srain.cube.demo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import in.srain.cube.demo.R;
import in.srain.cube.mints.base.BlockMenuFragment;
import in.srain.cube.demo.ui.activity.PagerTabIndicatorActivity;
import in.srain.cube.demo.ui.activity.imagelist.BigImageListActivity;
import in.srain.cube.demo.ui.activity.imagelist.GridListImageActivity;
import in.srain.cube.demo.ui.activity.imagelist.SmallListImageActivity;

import java.util.ArrayList;

public class HomeFragment extends BlockMenuFragment {

    @Override
    protected void addItemInfo(ArrayList<BlockMenuFragment.ItemInfo> itemInfos) {

        itemInfos.add(newItemInfo(R.string.cube_demo_load_big_image_in_activity, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), BigImageListActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_mid_image_in_activity, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), GridListImageActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_small_image_in_activity, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), SmallListImageActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_big_image_in_fragment, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_load_mid_image_in_fragment, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_load_small_image_in_fragment, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_view_holder_top_class, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_view_holder_inner_instance_class, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_view_holder_inner_static_class, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_more_list_view, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_load_more_grid_view, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_grid_view_with_header_and_footer, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo("API Request", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo("Dot View", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(DotViewFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo("More Action", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(MoreActionViewFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo("ImageLoader Management", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ImageLoaderManagementFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo("RequestCache Management", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestCacheManagementFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo("Tab", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Class<?> dstClassName = PagerTabIndicatorActivity.class;
                intent.setClass(getActivity(), dstClassName);
                startActivity(intent);
            }
        }));
    }

    @Override
    protected void setupViews() {
        setHeaderTitle("Cube App");
    }

}
