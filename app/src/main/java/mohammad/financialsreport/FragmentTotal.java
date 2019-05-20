package mohammad.financialsreport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohammad.financialsreport.customViews.MyTextView;

public class FragmentTotal extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_total,container,false);
        MyTextView pic = v.findViewById(R.id.pic);
        return v;
    }
}
