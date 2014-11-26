package in.srain.cube.demo.ui.list;

import android.widget.AbsListView;
import in.srain.cube.views.list.ListPageInfo;

public interface ILoadMore {

    public void setLoadMoreHandler(LoadMoreHandler loadMoreHandler);

    public void setOnScrollListener(AbsListView.OnScrollListener l);

    public void setStatusString(int firstLoading, int loadingMore, int empty, int noMore);

    public void loadFinish(ListPageInfo<?> page);
}
