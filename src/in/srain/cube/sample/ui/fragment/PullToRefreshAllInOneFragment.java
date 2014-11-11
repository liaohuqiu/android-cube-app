package in.srain.cube.sample.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import in.srain.cube.sample.ui.activity.PtrStoreHouseHeaderActivity;

import java.util.ArrayList;

public class PullToRefreshAllInOneFragment extends BlockMenuFragment {

    @Override
    protected void addItemInfo(ArrayList<ItemInfo> itemInfos) {
        itemInfos.add(new ItemInfo("Pull to Refresh", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Class<?> dstClassName = PtrStoreHouseHeaderActivity.class;
                intent.setClass(getActivity(), dstClassName);
                startActivity(intent);
            }
        }));
    }

    @Override
    protected void setupViews() {
        setHeaderTitle("Ultra Pull To Refresh");
    }

}
