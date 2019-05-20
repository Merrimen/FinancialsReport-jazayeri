package mohammad.financialsreport;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;


    public PagerAdapter(FragmentManager fm,int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentTotal();
            case 1:
                return new FragmentProject();
                default:
                    return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "کل";
            case 1:
                return "پروژه ها";
        }

        return super.getPageTitle(position);
    }
    @Override
    public int getCount() {
        return numOfTabs;
    }



}
