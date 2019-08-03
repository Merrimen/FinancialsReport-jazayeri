package mohammad.financialsreport.bottomsheetproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import mohammad.financialsreport.R;
import mohammad.financialsreport.customViews.MyTextView;

public class BottomSheetBaseAdapter extends BaseAdapter {

    Context mContext;
    String names[];

    public BottomSheetBaseAdapter(Context mContext, String[] names) {
        this.mContext = mContext;
        this.names = names;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.bottomsheet_list_item,parent,false);
        MyTextView name = v.findViewById(R.id.name);
        name.setText(names[position]);

        return v;
    }
}
