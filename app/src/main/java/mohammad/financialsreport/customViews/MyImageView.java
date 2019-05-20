package mohammad.financialsreport.customViews;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

public class MyImageView extends AppCompatImageView {
    Context mContext;

    public MyImageView(Context context) {
        super(context);
        Context mContext;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Context mContext;
    }

    public void load(String url) {
        Glide.with(mContext).load(url).into(this);
    }
}
