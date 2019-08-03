package mohammad.financialsreport.bottomsheetproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import mohammad.financialsreport.R;
import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.utilities.PublicMethods;


public class bottomsheet extends BottomSheetDialogFragment {
    private BottomSheetListener listener;
    ListView listView_Project;
    String names[];
    MyButton projectAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
        listView_Project = v.findViewById(R.id.listView_Project);
        String names[] = {
                "همه پروژه ها", "پروژه اول", "پروژه دوم", "پروژه سوم", "پروژه چهارم"
        };
        projectAll = v.findViewById(R.id.projectAll);
        BottomSheetBaseAdapter adapter = new BottomSheetBaseAdapter(getActivity(),names);
        listView_Project.setAdapter(adapter);
        listView_Project.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PublicMethods.toast(getActivity(), names[position]);

            }
        });

        return v;
    }

    public interface BottomSheetListener {
        int onButtonClicked(int positon);

    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try {
            listener = (BottomSheetListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement Bottomsheet listener");
        }
    }

}
