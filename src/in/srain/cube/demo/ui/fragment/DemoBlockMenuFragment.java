package in.srain.cube.demo.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import in.srain.cube.demo.R;
import in.srain.cube.mints.base.BlockMenuFragment;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public abstract class DemoBlockMenuFragment extends BlockMenuFragment {

    private ScrollView mScrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cube_app_home;
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
                    boolean ret = mScrollView.canScrollVertically(-1);
                    return ret;
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
