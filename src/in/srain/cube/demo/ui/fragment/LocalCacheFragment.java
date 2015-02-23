package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import in.srain.cube.cache.CacheManager;
import in.srain.cube.cache.CacheManagerFactory;
import in.srain.cube.cache.Query;
import in.srain.cube.cache.QueryJsonHandler;
import in.srain.cube.concurrent.SimpleExecutor;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.views.TitleAndValue;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.request.JsonData;
import in.srain.cube.util.CLog;

public class LocalCacheFragment extends TitleBaseFragment {

    private TitleAndValue mUid;
    private TitleAndValue mName;

    private static String KEY_FOR_USER_CACHE = "user";
    private LinearLayout mList;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeaderTitle(R.string.cube_demo_local_cache);

        View view = inflater.inflate(R.layout.fragment_form_local_cache, null);
        mList = (LinearLayout) view.findViewById(R.id.form_base_form_list);

        mUid = addTitleAndValue("uid:");
        mName = addTitleAndValue("name:");

        TextView readCacheTextView = (TextView) view.findViewById(R.id.local_cache_read_cache);
        readCacheTextView.setText(R.string.cube_demo_local_cache_read_cache);
        readCacheTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                readFromCache();
            }
        });

        TextView setCacheTextView = (TextView) view.findViewById(R.id.local_cache_set_cache);
        setCacheTextView.setText(R.string.cube_demo_local_cache_set_cache);
        setCacheTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setToCache();
            }
        });

        return view;
    }

    private void setToCache() {

        final JsonData jsonData = JsonData.newMap();
        jsonData.put("uid", mUid.getValue());
        jsonData.put("name", mName.getValue());

        CacheManager cacheManager = CacheManagerFactory.getDefault();
        cacheManager.setCacheData(KEY_FOR_USER_CACHE, jsonData.toString());
    }

    private TitleAndValue addTitleAndValue(String title) {
        TitleAndValue tv = new TitleAndValue(getContext());
        tv.title(title);
        mList.addView(tv);
        return tv;
    }

    private void readFromCache() {
        SimpleExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {

                Query<JsonData> query = new Query<JsonData>(CacheManagerFactory.getDefault());
                query.setCacheTime(86400 * 10);
                QueryJsonHandler queryJsonHandler = new QueryJsonHandler() {
                    @Override
                    public void onQueryFinish(Query.RequestType requestType, JsonData cacheData, boolean outOfDate) {
                        CLog.d("test", "onQueryFinish: %s %s %s", requestType, cacheData, outOfDate);
                        mUid.value(cacheData.optString("uid"));
                        mName.value(cacheData.optString("name"));
                    }

                    @Override
                    public String createDataForCache(Query<JsonData> query) {
                        CLog.d("test", "onNoCacheData");
                        return null;
                    }
                };
                query.setHandler(queryJsonHandler);
                query.setCacheKey(KEY_FOR_USER_CACHE);
                query.query();
            }
        });
    }
}