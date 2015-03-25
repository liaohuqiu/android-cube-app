package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import in.srain.cube.demo.R;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.list.ViewHolderCreator;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

import java.util.ArrayList;

public final class TestFragment extends Fragment {
    private ArrayList<String> mStringList = new ArrayList<String>();

    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();
        for (int i = 0; i < 20; i++) {
            fragment.mStringList.add(content);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_tab_indicator, container, false);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ListView listView = (ListView) view.findViewById(R.id.lv_frg_pager_tab);

        final ListViewDataAdapter<String> listViewDataAdapter = new ListViewDataAdapter<String>();

        ViewHolderCreator<String> viewHolderCreator = new ViewHolderCreator<String>() {
            @Override
            public ViewHolderBase<String> createViewHolder(int position) {

                // create different ViewHolder according the view type
                int type = listViewDataAdapter.getItemViewType(position);
                return new ViewHolder();
            }
        };

        listViewDataAdapter.setViewHolderCreator(viewHolderCreator);

        listView.setAdapter(listViewDataAdapter);
        listViewDataAdapter.getDataList().addAll(mStringList);
        listViewDataAdapter.notifyDataSetChanged();

        final PtrClassicFrameLayout frame = (PtrClassicFrameLayout) view.findViewById(R.id.frame_frg_pager_tab);
        frame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 1000);
            }
        });
        return view;
    }

    private class ViewHolder extends ViewHolderBase<String> {

        private TextView mTextView;

        @Override
        public View createView(LayoutInflater layoutInflater) {
            mTextView = new TextView(getActivity());
            return mTextView;
        }

        @Override
        public void showData(int position, String itemData) {
            mTextView.setText(itemData);
        }
    }
}
