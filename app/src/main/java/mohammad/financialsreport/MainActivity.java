package mohammad.financialsreport;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import mohammad.financialsreport.bottomsheetproject.bottomsheet;
import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.customViews.MyImageView;
import mohammad.financialsreport.customViews.MyTextView;
import mohammad.financialsreport.dialogFragment.FragmentAccount;
import mohammad.financialsreport.dialogFragment.FragmentDialog;
import mohammad.financialsreport.models.PaymentEntity;
import mohammad.financialsreport.models.ProjectEntity;
import mohammad.financialsreport.utilities.AppExecutors;
import mohammad.financialsreport.utilities.PublicMethods;

public class MainActivity extends AppCompatActivity implements bottomsheet.BottomSheetListener, FragmentAccount.OnFragmentInteraction {

    public static final String USERID = "userid";

    private MyTextView reports, creditor, debtor, dateStart, dateEnd;
    private MyImageView arrowdown, arrowup, check_account;
    private TabLayout tabs;
    private ViewPager pager;
    private TabItem tabItem1;
    private TabItem tabitem2;
    private MyButton btn_account;
    private MyButton projectAll;
    private String m_Text = "";
    private bottomsheet bottomsheet1;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        bind();
        intent();
        info1();
        //this is for viewpager
        viewPager();

        //this showing account
        btn_account.setOnClickListener(V -> {
            onOpenDialog();

        });

        // this is for bottomsheet
        findViewById(R.id.projectAll).setOnClickListener(V -> {
            bottomsheet bottomsheet1 = new bottomsheet();
            bottomsheet1.show(getSupportFragmentManager(), "پروژه ها");

        });

        //this is for picker Date
        findViewById(R.id.btndate).setOnClickListener(V -> {
            pickerDateStart();
        });
        findViewById(R.id.btndate2).setOnClickListener(V -> {
            pickerDateEnd();
        });
        findViewById(R.id.dateStart).setOnClickListener(V -> {
            pickerDateStart();
        });
        findViewById(R.id.dateEnd).setOnClickListener(V -> {
            pickerDateEnd();
        });

    }

    void bind() {
        reports = findViewById(R.id.reports);
        creditor = findViewById(R.id.creditor);
        debtor = findViewById(R.id.debtor);
        arrowup = findViewById(R.id.arrowup);
        arrowdown = findViewById(R.id.arrowdown);
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        tabItem1 = findViewById(R.id.tabitem1);
        tabitem2 = findViewById(R.id.tabitem2);
        dateStart = findViewById(R.id.dateStart);
        dateEnd = findViewById(R.id.dateEnd);
        projectAll = findViewById(R.id.projectAll);
        btn_account = findViewById(R.id.btn_account);
        check_account = findViewById(R.id.check_account);

    }

    public void info1() {
        AppExecutors executors = AppExecutors.getInstance();
        executors.diskIO().execute(() -> {
            ProjectEntity projectEntity = AppDatabase.getInstance(this).getAllProjectEntityDAO().getUseById(userid);
            PaymentEntity paymentEntity = AppDatabase.getInstance(this).getAllPaymentEntityDAO().getUseById(userid);

            executors.mainThread().execute(() -> checkPayment(projectEntity, paymentEntity));
        });

    }

    private void checkPayment(ProjectEntity projectEntity, PaymentEntity paymentEntity) {
        creditor.setText(getString(R.string.paid) + ":");
        debtor.setText(getString(R.string.remain) + ":");
        if (projectEntity != null & paymentEntity != null) {
            creditor.setText(getString(R.string.paid) + ":" + " " + String.valueOf(projectEntity.getTotalSalary()) + getString(R.string.Rial));
            debtor.setText(getString(R.string.remain) + ":" + " " + String.valueOf(paymentEntity.getDeposited() - projectEntity.getTotalSalary()) + getString(R.string.Rial));
        }

    }

    //dialog for inserting account for user
    public void onOpenDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentDialog overlay = new FragmentDialog();
        overlay.show(fm, FragmentDialog.TAG);
    }

    //this is for pickerDate Start
    void pickerDateStart() {
        PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString(getString(R.string.ok_bashe))
                .setNegativeButton(getString(R.string.never_mind))
                .setTodayButton(getString(R.string.today))
                .setTodayButtonVisible(true)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        dateStart.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        picker.show();
    }

    //this is for pickerDate END
    void pickerDateEnd() {
        PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString(getString(R.string.ok_bashe))
                .setNegativeButton(getString(R.string.never_mind))
                .setTodayButton(getString(R.string.today))
                .setTodayButtonVisible(true)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        dateEnd.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        picker.show();
    }

    void intent() {
        userid = getIntent().getIntExtra(USERID, -1);
        PublicMethods.toast(this, "userid: " + userid);
    }

    //this is for viewpager
    void viewPager() {
        Bundle args = new Bundle();
        args.putInt(FragmentTotal.USERID, userid);
        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabs.getTabCount(), args);
        pager.setAdapter(pageAdapter);
        tabs.setupWithViewPager(pager);
    }

    @Override
    public void onDismiss() {
        DialogFragment df = (DialogFragment) getSupportFragmentManager().findFragmentByTag(FragmentDialog.TAG);
        if (df != null)
            df.dismiss();
    }

    // this function is related to bottomsheete
    @Override
    public int onButtonClicked(int positon) {
        return 0;
    }
}
