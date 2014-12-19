package in.srain.cube.demo.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.activity.PagerTabIndicatorActivity;
import in.srain.cube.demo.ui.imageloader.ImageLoaderHomeFragment;
import in.srain.cube.demo.ui.loadmore.LoadMoreHomeFragment;
import in.srain.cube.demo.ui.request.RequestHomeFragment;
import in.srain.cube.demo.ui.viewholderdemo.ViewHolderHomeFragment;
import in.srain.cube.mints.base.MenuItemFragment;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

import java.util.ArrayList;

public class DemoHomeFragment extends MenuItemFragment {

    @Override
    protected void addItemInfo(ArrayList<MenuItemInfo> itemInfos) {

        itemInfos.add(newItemInfo(R.string.cube_demo_block_image_loader, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ImageLoaderHomeFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_view_holder, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(ViewHolderHomeFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_slider_banner, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(SliderBannerFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_load_more_demo, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LoadMoreHomeFragment.class, null);
            }
        }));
        itemInfos.add(newItemInfo(R.string.cube_demo_request_demo, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(RequestHomeFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_grid_view_with_header_and_footer, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(GridViewWithHeaderAndFooterFragment.class, null);
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
    protected void setupViews(View view) {
        setHeaderTitle(R.string.cube_demo_home_title);

        final PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.fragment_home_ptr_frame);
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString(getString(R.string.cube_demo_home_title));

        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
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

        final ListViewDataAdapter<MenuItemInfo> adapter = new ListViewDataAdapter<MenuItemInfo>();
        adapter.setViewHolderClass(getContext(), ViewHolder.class);

        ListView listView = (ListView) view.findViewById(R.id.fragment_home_menu_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.getItem(position).onClick(view);
            }
        });
        listView.setAdapter(adapter);
        adapter.getDataList().addAll(mItemInfos);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    static class ViewHolder extends ViewHolderBase<MenuItemInfo> {

        private TextView mTitleTextView;

        @Override
        public View createView(LayoutInflater layoutInflater) {
            View view = layoutInflater.inflate(R.layout.home_menu_list_item, null);
            mTitleTextView = (TextView) view.findViewById(R.id.home_menu_list_item_title);
            return view;
        }

        @Override
        public void showData(int position, MenuItemInfo itemData) {
            mTitleTextView.setBackgroundColor(itemData.getColor());
            mTitleTextView.setText(itemData.getTitle());
        }
    }
}
