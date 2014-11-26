package in.srain.cube.demo.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import in.srain.cube.demo.R;

public class ListFooterView extends RelativeLayout {

    private TextView mTextView;

    public ListFooterView(Context context) {
        this(context, null);
    }

    public ListFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.views_loadmore_footer, this);
        mTextView = (TextView) findViewById(R.id.tv_search_views_list_footer);
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    public void setText(int id) {
        mTextView.setText(id);
    }
}
