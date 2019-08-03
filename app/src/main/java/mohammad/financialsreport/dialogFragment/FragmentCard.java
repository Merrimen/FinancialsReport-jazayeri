package mohammad.financialsreport.dialogFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohammad.financialsreport.utilities.PublicMethods;
import mohammad.financialsreport.R;
import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.customViews.MyEditText;

public class FragmentCard extends Fragment {
    private MyEditText card_number, name_card_owner, name_bank_card;
   private MyButton insertCard;

    static FragmentCard fragment;

    public static FragmentCard getInstance() {
        if (fragment == null)
            fragment = new FragmentCard();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card, container, false);
        card_number = v.findViewById(R.id.card_number);
        name_card_owner = v.findViewById(R.id.name_card_owner);
        name_bank_card = v.findViewById(R.id.name_bank_card);
        insertCard = v.findViewById(R.id.insertCard);

        insertCard.setOnClickListener(V -> {
            if (V.getId() == R.id.insertCard) {
              insertcard();
            }
        });
        return v;
    }

    void insertcard(){
        String card_number_value = card_number.text();
        String name_card_owner_value = name_card_owner.text();
        String name_bank_card_value = name_bank_card.text();
        if (!card_number_value.isEmpty() & !name_card_owner_value.isEmpty() & !name_bank_card_value.isEmpty()) {
            PublicMethods.toast(getActivity(),getString(R.string.successfulyInsert));
        }else {PublicMethods.toast(getActivity(),getString(R.string.ErrorInsertAccount));

        };

    }
}
