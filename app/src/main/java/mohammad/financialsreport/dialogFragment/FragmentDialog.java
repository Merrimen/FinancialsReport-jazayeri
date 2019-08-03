package mohammad.financialsreport.dialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import mohammad.financialsreport.FragmentProject;
import mohammad.financialsreport.FragmentTotal;
import mohammad.financialsreport.R;

public class FragmentDialog extends DialogFragment {
    public static final String TAG = "FragmentDialog";
    private TabLayout tabLayout;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager pagerDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Log.d(TAG, "onCreateDialog: ");

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);

        tabLayout = v.findViewById(R.id.tabLayout);
        sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        pagerDialog = v.findViewById(R.id.pagerDialog);
        pagerDialog.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(pagerDialog);

//        Bundle args = new Bundle();
//        args.putInt(FragmentAccount.TYPE, 0);

        return v;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Bundle args;
        public SectionsPagerAdapter(FragmentManager fm,Bundle args) {
            super(fm);
            args = this.args;

        }
        //this is for solving bug
        public SectionsPagerAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return FragmentAccount.getInstance();
                case 1:
                    return FragmentCard.getInstance();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "شماره حساب";
                case 1:
                    return "شماره کارت";
            }

            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
