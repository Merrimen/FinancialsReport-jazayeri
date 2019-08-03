package mohammad.financialsreport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    Bundle args;
    public PagerAdapter(FragmentManager fm, int numOfTabs, Bundle args) {
        super(fm);
        this.args = args;
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentTotal.getInstance(args);
            case 1:
                return FragmentProject.getInstance(args);
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "کارکرد";
            case 1:
                return "تسویه";
        }

        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }


}
