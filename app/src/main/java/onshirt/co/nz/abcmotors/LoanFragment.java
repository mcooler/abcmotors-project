package onshirt.co.nz.abcmotors;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoanFragment extends Fragment {
    Button CalcBtn, ApplyNow;
    RadioGroup radioGroup_interest, radioGroup_term;
    RadioButton radioButton_interest, radioButton_term;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_loan, null);

        final EditText total_loan = view.findViewById(R.id.total_loan);

        radioGroup_interest = view.findViewById(R.id.interest);
        radioGroup_term = view.findViewById(R.id.term);


        final EditText result_weekly_repayment = view.findViewById(R.id.weekly_repayment);
        final EditText result_monthly_repayment = view.findViewById(R.id.monthly_repayment);

        CalcBtn = view.findViewById(R.id.btn_calculate2);

        CalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string_total_loan = total_loan.getText().toString();
                int selected_id = radioGroup_interest.getCheckedRadioButtonId();

                // the 2 radio selection
                radioButton_interest = view.findViewById(selected_id);
                String string_interest = radioButton_interest.getText().toString();

                radioButton_term = view.findViewById(selected_id);
                String string_term = radioButton_term.getText().toString();


                if (TextUtils.isEmpty(string_total_loan)) {
                    total_loan.setError("Enter Loan Amount");
                    total_loan.requestFocus();
                    return;
                }


                float float_total_loan = Float.parseFloat(string_total_loan);
                float float_interest = Float.parseFloat(string_interest);
                float float_term = Float.parseFloat(string_term);

                float total_month = float_term;

                //calcuation:
                float weekly_repayment = ((float_total_loan*float_interest/100)+float_total_loan)/52;
                float monthly_repayment = ((float_total_loan*float_interest/100)+float_total_loan)/total_month;


                result_weekly_repayment.setText(String.valueOf("NZ$ " +weekly_repayment + " per week"));
                result_monthly_repayment.setText(String.valueOf("NZ$ " + monthly_repayment + " per month"));
            }
        });


        ApplyNow = view.findViewById(R.id.apply_now);
        ApplyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Please Login First", Toast.LENGTH_LONG).show();

            }
        });




        return view;


    }
}