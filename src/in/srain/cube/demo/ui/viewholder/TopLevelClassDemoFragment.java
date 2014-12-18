package in.srain.cube.demo.ui.viewholder;

import in.srain.cube.demo.R;
import in.srain.cube.views.list.ListViewDataAdapter;

public class TopLevelClassDemoFragment extends ViewHolderDemoBaseFragment {

    protected void setupViews(ListViewDataAdapter<String> adapter) {
        setHeaderTitle(R.string.cube_demo_view_holder_top_class);
        adapter.setViewHolderClass(this, TopLevelViewHolder.class, getImageLoader());
    }
}
