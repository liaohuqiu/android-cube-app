package in.srain.cube.demo.ui.loadmore;

import android.view.View;
import android.view.View.OnClickListener;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.fragment.DemoBlockMenuFragment;
import in.srain.cube.demo.ui.viewholder.NestedStaticClassFragment;

import java.util.ArrayList;

public class LoadMoreHomeFragment extends DemoBlockMenuFragment {

    @Override
    protected void addItemInfo(ArrayList<MenuItemInfo> itemInfos) {
        itemInfos.add(newItemInfo(R.string.cube_demo_view_holder_top_class, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(LoadMoreListFragment.class, null);
            }
        }));

        itemInfos.add(newItemInfo(R.string.cube_demo_view_holder_static_nested_class, "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(NestedStaticClassFragment.class, null);
            }
        }));
    }

    @Override
    protected void setupViews(View view) {
        setHeaderTitle(R.string.cube_demo_load_more_demo);
        super.setupViews(view);
    }
}
