package mohammad.financialsreport;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import ir.hamsaa.persiandatepicker.view.PersianNumberPicker;
import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.models.PaymentEntity;
import mohammad.financialsreport.models.ProjectEntity;
import mohammad.financialsreport.utilities.AppExecutors;

public class FragmentTotal extends Fragment {
    private static final String TAG = "FragmentTotal";

    public static final String USERID = "userid";
    MyButton function, reward, tax, penalty;
    private int userid;

    //this is for fragment
    static FragmentTotal fragment;


    public static FragmentTotal getInstance(Bundle args) {
        if (fragment == null)
            fragment = new FragmentTotal();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userid = getArguments().getInt(USERID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_total, container, false);
        Log.d(TAG, "onCreateView: useid:" + userid);
        PieChart pieChart = v.findViewById(R.id.piechart);
        function = v.findViewById(R.id.function);
        reward = v.findViewById(R.id.reward);
        tax = v.findViewById(R.id.tax);
        penalty = v.findViewById(R.id.penalty);

        info1();


//this is for pie chart
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 3, 0, 5);
        pieChart.setDragDecelerationFrictionCoef(0.15f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(25f, getString(R.string.Bonus)));
        yValues.add(new PieEntry(60f, getString(R.string.operation)));
        yValues.add(new PieEntry(14f, getString(R.string.fine)));
        yValues.add(new PieEntry(10f, getString(R.string.Taxes)));

        Description description = new Description();
        description.setText(getString(R.string.app_name));
        description.setTextSize(15);
        pieChart.setDescription(description);
        pieChart.animateY(1800, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(10f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(14f);
        dataSet.setColors(Color.BLUE, Color.GREEN, Color.RED, Color.GRAY);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        return v;

    }

    public void info1() {
        AppExecutors executors = AppExecutors.getInstance();
        executors.diskIO().execute(() -> {
            ProjectEntity projectEntity = AppDatabase.getInstance(getActivity()).getAllProjectEntityDAO().getUseById(userid);

            executors.mainThread().execute(() -> checkPayment(projectEntity));
        });

    }

    private void checkPayment(ProjectEntity projectEntity) {
        function.setText(getString(R.string.operation) + ":");
        reward.setText(getString(R.string.Bonus) + ":");
        tax.setText(getString(R.string.Taxes)+ ":");
        penalty.setText(getString(R.string.fine)+ ":");
        if (projectEntity != null) {
            function.setText(getString(R.string.operation) + ":" + " " + String.valueOf(projectEntity.getFunction()) + " " + getString(R.string.Rial));
            reward.setText(getString(R.string.Bonus) + ":" + " " + String.valueOf(projectEntity.getReward()) + " " + getString(R.string.Rial));
            tax.setText(getString(R.string.Taxes) + ":" + " " + String.valueOf(projectEntity.getTax()) + " " + getString(R.string.Rial));
            penalty.setText(getString(R.string.fine) + ":" + " " + String.valueOf(projectEntity.getPenalty()) + " " + getString(R.string.Rial));
        }

    }
}


