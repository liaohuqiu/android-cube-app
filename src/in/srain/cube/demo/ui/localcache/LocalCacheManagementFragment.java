package in.srain.cube.demo.ui.localcache;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import in.srain.cube.cache.CacheManager;
import in.srain.cube.cache.CacheManagerFactory;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.views.TitleAndValue;
import in.srain.cube.mints.base.TitleBaseFragment;

public class LocalCacheManagementFragment extends TitleBaseFragment {

    private TitleAndValue mFileCachePath;
    private TitleAndValue mFileCacheMax;
    private TitleAndValue mFileCacheUsed;
    private TitleAndValue mMemoryCacheMax;
    private TitleAndValue mMemoryCacheUsed;

    private CacheManager mCacheManager;
    private LinearLayout mList;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeaderTitle(R.string.cube_demo_local_cache_management);

        View view = inflater.inflate(R.layout.fragment_form_base, null);
        mList = (LinearLayout) view.findViewById(R.id.form_base_form_list);

        mFileCachePath = addTitleAndValue("file cache path:");
        mFileCacheMax = addTitleAndValue("file cache max:");
        mFileCacheUsed = addTitleAndValue("file cache used:");

        mMemoryCacheMax = addTitleAndValue("memory max:");
        mMemoryCacheUsed = addTitleAndValue("memory used:");

        mCacheManager = CacheManagerFactory.getDefault();

        TextView textView = (TextView) view.findViewById(R.id.form_base_button);
        textView.setText(R.string.cube_demo_cache_manager_clear_cache);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mCacheManager.clearMemoryCache();
                mCacheManager.clearDiskCache();
                update();
            }
        });

        update();
        return view;
    }

    private TitleAndValue addTitleAndValue(String title) {
        TitleAndValue tv = new TitleAndValue(getContext());
        tv.title(title);
        mList.addView(tv);
        return tv;
    }

    private void update() {

        mFileCachePath.value(mCacheManager.getFileCachePath());
        mFileCacheMax.value(mCacheManager.getFileCacheMaxSpace() / 1024f + "KB");
        mFileCacheUsed.value(mCacheManager.getFileCacheUsedSpace() / 1024f + "KB");

        mMemoryCacheMax.value(mCacheManager.getMemoryCacheMaxSpace() / 1024f + "KB");
        mMemoryCacheUsed.value(mCacheManager.getMemoryCacheUsedSpace() / 1024f + "KB");
    }
}