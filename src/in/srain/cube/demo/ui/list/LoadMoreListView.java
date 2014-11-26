package in.srain.cube.demo.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
import in.srain.cube.demo.R;
import in.srain.cube.views.list.ListPageInfo;

/**
 * @author huqiu.lhq
 */
public class LoadMoreListView extends ListView implements ILoadMore {

    private OnScrollListener mOnScrollListener;
    private LoadMoreHandler mLoadMoreHandler;

    private ListFooterView mFooterView;

    private boolean mIsLoading;
    private boolean mHasMore = true;

    private int mStatusStringFirstLoading = R.string.base_loadmore_loading;
    private int mStatusStringLoadingMore = R.string.base_loadmore_loading;
    private int mStatusStringNoMore = R.string.base_loadmore_no_more;
    private int mStatusStringEmpty = R.string.base_loadmore_empty;

    public LoadMoreListView(Context context) {
        this(context, null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        mFooterView = new ListFooterView(getContext());
        addFooterView(mFooterView);

        super.setOnScrollListener(new OnScrollListener() {

            private boolean mIsEnd = false;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (null != mOnScrollListener) {
                    mOnScrollListener.onScrollStateChanged(view, scrollState);
                }
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (mIsEnd) {
                        onReachBottom();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (null != mOnScrollListener) {
                    mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
                if (firstVisibleItem + visibleItemCount >= totalItemCount - 1) {
                    mIsEnd = true;
                } else {
                    mIsEnd = false;
                }
            }
        });

        mFooterView.setVisibility(VISIBLE);
        mFooterView.setText(mStatusStringFirstLoading);
    }

    private void onReachBottom() {
        if (null != mLoadMoreHandler && !mIsLoading && mHasMore) {
            mIsLoading = true;
            if (mStatusStringLoadingMore < 0) {
                mFooterView.setVisibility(GONE);
            } else {
                mFooterView.setVisibility(VISIBLE);
                mFooterView.setText(mStatusStringLoadingMore);
            }
            mLoadMoreHandler.onLoadMore();
        }
    }

    public void loadFinish(ListPageInfo<?> page) {
        mHasMore = page.hasMore();
        mIsLoading = false;
        if (mHasMore) {
            mFooterView.setVisibility(GONE);
        } else {
            if (page.isEmpty()) {
                if (mStatusStringEmpty < 0) {
                    mFooterView.setVisibility(GONE);
                } else {
                    mFooterView.setVisibility(VISIBLE);
                    mFooterView.setText(mStatusStringEmpty);
                }
            } else {
                if (mStatusStringNoMore < 0) {
                    mFooterView.setVisibility(GONE);
                } else {
                    mFooterView.setVisibility(VISIBLE);
                    mFooterView.setText(mStatusStringNoMore);
                }
            }
        }
    }

    public void setStatusString(int firstLoading, int loadingMore, int empty, int noMore) {
        if (firstLoading > 0) {
            mStatusStringFirstLoading = firstLoading;
        }
        if (loadingMore != 0) {
            mStatusStringLoadingMore = loadingMore;
        }
        if (empty != 0) {
            mStatusStringEmpty = empty;
        }
        if (noMore != 0) {
            mStatusStringNoMore = noMore;
        }
    }

    public void setLoadMoreHandler(LoadMoreHandler loadMoreHandler) {
        mLoadMoreHandler = loadMoreHandler;
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
    }

    public ListFooterView getFooterStatusView() {
        return mFooterView;
    }
}