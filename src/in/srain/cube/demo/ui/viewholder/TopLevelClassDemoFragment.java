package in.srain.cube.demo.ui.viewholder;

import in.srain.cube.views.list.ListViewDataAdapter;

public class TopLevelClassDemoFragment extends ViewHolderDemoBaseFragment {

    protected void setupAdapter(ListViewDataAdapter<String> adapter) {
        adapter.setViewHolderClass(this, TopLevelViewHolder.class, mImageLoader);
    }
}
