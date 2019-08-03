package mohammad.financialsreport;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.models.PaymentEntity;
import mohammad.financialsreport.models.ProjectEntity;
import mohammad.financialsreport.utilities.AppExecutors;


public class FragmentProject extends Fragment {
    public static final String USERID = "userid";
    PieChart piechartProject;
    static FragmentProject fragment;
    private int userid;
    MyButton deposited, remain;

    //this is for fragment
    public static FragmentProject getInstance(Bundle args) {
        if (fragment == null)
            fragment = new FragmentProject();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userid = getArguments().getInt(USERID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        PieChart piechartProject = v.findViewById(R.id.piechartProject);
        deposited = v.findViewById(R.id.deposited);
        remain = v.findViewById(R.id.remain);
        info1();
        //this is for pie chart
        piechartProject.setUsePercentValues(true);
        piechartProject.getDescription().setEnabled(false);
        piechartProject.setExtraOffsets(0, 0, 0, 0);
        piechartProject.setDragDecelerationFrictionCoef(0.15f);
        piechartProject.setDrawHoleEnabled(true);
        piechartProject.setHoleColor(Color.WHITE);
        piechartProject.setTransparentCircleRadius(60f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(40f, getString(R.string.remain_money)));
        yValues.add(new PieEntry(60f, getString(R.string.paid)));
        Description description = new Description();
        description.setText(getString(R.string.clearing_report));
        description.setTextSize(15);
        piechartProject.setDescription(description);
        piechartProject.animateY(1800, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(10f);
        dataSet.setSelectionShift(15f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(14f);
        dataSet.setColors(Color.BLUE, Color.GREEN);
        data.setValueTextColor(Color.WHITE);
        piechartProject.setData(data);

        return v;

    }

    public void info1() {
        AppExecutors executors = AppExecutors.getInstance();
        executors.diskIO().execute(() -> {
            PaymentEntity paymentEntity = AppDatabase.getInstance(getActivity()).getAllPaymentEntityDAO().getUseById(userid);
            ProjectEntity projectEntity = AppDatabase.getInstance(getActivity()).getAllProjectEntityDAO().getUseById(userid);
            executors.mainThread().execute(() -> checkPayment(paymentEntity, projectEntity));
        });

    }

    private void checkPayment(PaymentEntity paymentEntity, ProjectEntity projectEntity) {
        deposited.setText(getString(R.string.operation) + ":");
        remain.setText(getString(R.string.remain) + ":");
        if (paymentEntity != null) {
            deposited.setText(getString(R.string.operation) + ":" + " " + String.valueOf(paymentEntity.getDeposited()) + " " + getString(R.string.Rial));
            remain.setText(getString(R.string.remain) + ":" + " " + String.valueOf(paymentEntity.getDeposited() - projectEntity.getTotalSalary()) + " " + getString(R.string.Rial));
        }

    }
}