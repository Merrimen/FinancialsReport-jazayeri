package mohammad.financialsreport.dialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.constraint.Placeholder;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohammad.financialsreport.customViews.MyImageView;
import mohammad.financialsreport.utilities.PublicMethods;
import mohammad.financialsreport.R;
import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.customViews.MyEditText;

public class FragmentAccount extends Fragment {
    //    public static final String TYPE = "type";
//    private int type;
    private MyEditText account_number, name_account_owner, name_bank_account;
    private MyButton insertAccount;
    static FragmentAccount fragment;
    private OnFragmentInteraction listener;

    public static FragmentAccount getInstance() {
        if (fragment == null)
            fragment = new FragmentAccount();
//        fragment.setArguments();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acoount, container, false);
        account_number = v.findViewById(R.id.account_number);
        name_account_owner = v.findViewById(R.id.name_account_owner);
        name_bank_account = v.findViewById(R.id.name_bank_account);
        insertAccount = v.findViewById(R.id.insertAccount);
        loadUser();

        insertAccount.setOnClickListener(V -> {
            if (V.getId() == R.id.insertAccount) {
                insertAccount();
            }
        });
        return v;
    }

    private void insertAccount() {
        String account_number_value = account_number.text();
        String name_account_owner_value = name_account_owner.text();
        String name_bank_account_value = name_bank_account.text();
        PublicMethods.setData("account_number", account_number_value);
        PublicMethods.setData("name_account_owner", name_account_owner_value);
        PublicMethods.setData("name_bank_account", name_bank_account_value);
        if (!account_number_value.isEmpty() & !name_account_owner_value.isEmpty() & !name_bank_account_value.isEmpty()) {
            PublicMethods.toast(getActivity(), getString(R.string.successfulyInsert));
            listener.onDismiss();
        } else {
            PublicMethods.toast(getActivity(), getString(R.string.ErrorInsertAccount));
        }
    }

    private void loadUser() {
        String currentUser_account_number = PublicMethods.getData("account_number");
        String currentUser_name_account_owner = PublicMethods.getData("name_account_owner");
        String currentUser_name_bank_account = PublicMethods.getData("name_bank_account");
        account_number.setText(currentUser_account_number);
        name_account_owner.setText(currentUser_name_account_owner);
        name_bank_account.setText(currentUser_name_bank_account);


    }

    public interface OnFragmentInteraction {
        void onDismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteraction) {
            listener = (OnFragmentInteraction) context;
        }
    }

}
