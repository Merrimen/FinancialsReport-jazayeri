package mohammad.financialsreport;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import mohammad.financialsreport.customViews.MyImageView;
import mohammad.financialsreport.customViews.MyTextView;

public class MainActivity extends AppCompatActivity {

    MyTextView reports,creditor,debtor;
    MyImageView arrowdown,arrowup;
    TabLayout tabs;
    ViewPager pager;
    TabItem tabItem1;
    TabItem tabitem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        bind();
        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(),tabs.getTabCount());
        pager.setAdapter(pageAdapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

    }

    void bind(){
        reports = findViewById(R.id.reports);
        creditor = findViewById(R.id.creditor);
        debtor = findViewById(R.id.debtor);
        arrowup = findViewById(R.id.arrowup);
        arrowdown = findViewById(R.id.arrowdown);
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
       tabItem1 = findViewById(R.id.tabitem1);
       tabitem2 = findViewById(R.id.tabitem2);
    }


}
