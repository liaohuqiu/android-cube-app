package in.srain.cube.demo.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ScrollView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.activity.PagerTabIndicatorActivity;
import in.srain.cube.demo.ui.imagelist.activity.LoadBigImageInActivity;
import in.srain.cube.demo.ui.imagelist.activity.LoadMidImageInActivity;
import in.srain.cube.demo.ui.imagelist.activity.LoadSmallImageInActivity;
import in.srain.cube.demo.ui.imagelist.fragment.LoadBigImageFragment;
import in.srain.cube.demo.ui.imagelist.fragment.LoadMidImageInFragment;
import in.srain.cube.demo.ui.imagelist.fragment.LoadSmallImageInFragment;
import in.srain.cube.mints.base.BlockMenuFragment;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

import java.util.ArrayList;

public class HomeFragment extends BlockMenuFragment {

    private ScrollView mScrollView;

    @Override
    protected void addItemInfo(ArrayList<BlockMenuFragment.ItemInfo> itemInfos) {

        itemInfos.add(newItemInfo(R.string.cube_demo_load_big_image_in_activity, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), LoadBigImageInActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_mid_image_in_activity, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), LoadMidImageInActivity.class);
                startActivity(intent);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_small_image_in_activity, "#4d90fe", new OnClickListener() {

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
        itemInfos.add(newItemInfo(R.string.cube_demo_block_pre_load_image, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(PreLoadImageFragment.class, null);
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmengt_cube_app_home;
    }

    @Override
    protected void setupViews() {
        setHeaderTitle(R.string.cube_demo_home_title);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.createView(inflater, container, savedInstanceState);
        mScrollView = (ScrollView) view.findViewById(R.id.fragment_cube_app_home_scroll_view);
        view.setBackgroundColor(getResources().getColor(R.color.cube_mints_333333));

        final PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.fragment_cube_app_home_ptr_frame);
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString(getString(R.string.cube_demo_home_title));

        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (Build.VERSION.SDK_INT >= 14) {
                    // boolean ret = mScrollView.canScrollVertically(-1);
                    // return ret;
                }
                return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });
        return view;
    }

}
