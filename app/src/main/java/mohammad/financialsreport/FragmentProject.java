package mohammad.financialsreport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohammad.financialsreport.customViews.MyTextView;

public class FragmentProject extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project,container,false);
        MyTextView project = v.findViewById(R.id.project);
        
        return v;
    }
}
