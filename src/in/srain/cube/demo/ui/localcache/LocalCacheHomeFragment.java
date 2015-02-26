package in.srain.cube.demo.ui.localcache;

import android.view.View;
import android.view.View.OnClickListener;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.fragment.DemoBlockMenuFragment;

import java.util.ArrayList;

public class LocalCacheHomeFragment extends DemoBlockMenuFragment {

    @Override
    protected void addItemInfo(ArrayList<MenuItemInfo> itemInfos) {
        itemInfos.add(newItemInfo(R.string.cube_demo_local_cache, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LocalCacheFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_local_cache_management, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LocalCacheManagementFragment.class, null);
            }
        }));
    }

    @Override
    protected void setupViews(View view) {
        setHeaderTitle(R.string.cube_demo_local_cache);
        super.setupViews(view);
    }
}
