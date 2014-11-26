package in.srain.cube.demo.ui.list;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import in.srain.cube.demo.R;

public class ListStatus {

    private Status mStatus;
    private CharSequence mCharSequence;

    private ListStatus(Status status, CharSequence text) {
        mStatus = status;
        mCharSequence = text;
    }

    public static ListStatus createLoadingStatus(Context context) {
        ListStatus listStatus = new ListStatus(Status.loading, context.getString(R.string.search_footer_loading));
        return listStatus;
    }

    public static ListStatus createNotFoundStatus(Context context, String searchQuery) {

        String pre = context.getString(R.string.search_footer4_1);
        String post = context.getString(R.string.search_footer4_3);

        SpannableString ss = new SpannableString(pre + searchQuery + post);
        ss.setSpan(new ForegroundColorSpan(Color.RED), pre.length(), pre.length() + searchQuery.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ListStatus listStatus = new ListStatus(Status.list_data_empty, ss);
        return listStatus;
    }

    public static ListStatus createNoMoreStatus(Context context) {
        String ss = "所有内容均已加载完毕";
        ListStatus listStatus = new ListStatus(Status.list_data_no_more, ss);
        return listStatus;
    }

    public static ListStatus createDataLoaed(Context context) {
        String ss = "";
        ListStatus listStatus = new ListStatus(Status.list_data_loaded, ss);
        return listStatus;
    }

    public Status getStatus() {
        return mStatus;
    }

    public CharSequence getText() {
        return mCharSequence;
    }

    public enum Status {
        loading, list_data_loaded, list_data_empty, list_data_no_more
    }
}
