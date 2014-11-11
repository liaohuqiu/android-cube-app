package in.srain.cube.sample.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import in.srain.cube.sample.R;
import in.srain.cube.sample.ui.activity.PagerTabIndicatorActivity;
import in.srain.cube.sample.ui.activity.imagelist.BigImageListActivity;
import in.srain.cube.sample.ui.activity.imagelist.GridListImageActivity;
import in.srain.cube.sample.ui.activity.imagelist.SmallListImageActivity;

import java.util.ArrayList;

public class HomeFragment extends BlockMenuFragment {

    @Override
    protected void addItemInfo(ArrayList<BlockMenuFragment.ItemInfo> itemInfos) {

        itemInfos.add(new ItemInfo("Big Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), BigImageListActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(new ItemInfo("Grid Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), GridListImageActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(new ItemInfo("Small Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), SmallListImageActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(new ItemInfo("API Request", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestDemoFragment.class, null);
            }
        }));

        itemInfos.add(new ItemInfo("Dot View", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(DotViewFragment.class, null);
            }
        }));

        itemInfos.add(new ItemInfo("More Action", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(MoreActionViewFragment.class, null);
            }
        }));
        itemInfos.add(new ItemInfo("ImageLoader Management", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ImageLoaderManagementFragment.class, null);
            }
        }));

        itemInfos.add(new ItemInfo("RequestCache Management", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestCacheManagementFragment.class, null);
            }
        }));
        itemInfos.add(new ItemInfo("Tab", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Class<?> dstClassName = PagerTabIndicatorActivity.class;
                intent.setClass(getActivity(), dstClassName);
                startActivity(intent);
            }
        }));
        itemInfos.add(newItemInfo(R.string.ptr_all_in_one, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(PullToRefreshAllInOneFragment.class, null);
            }
        }));
    }

    @Override
    protected void setupViews() {
        setHeaderTitle("Cube App");
    }

}
