package com.moneyger_v2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//A fragment used for creating new budget
public class newBudget extends Fragment {
    View view;
    Button fragmentButton;
    String name;
    int value;
    EditText budgetName;
    EditText budgetValue;
    Button saveBudget;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_budget, container, false);
        fragmentButton = (Button)view.findViewById(R.id.newBudget);

        budgetName = view.findViewById(R.id.budgetName);
        budgetValue = view.findViewById(R.id.budgetValue);

        saveBudget = (Button) view.findViewById(R.id.saveBudget);
        saveBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(budgetName.getText().toString()) || budgetName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Please input the budget name", Toast.LENGTH_SHORT).show();
                } else {
                    name = budgetName.getText().toString();
                    if (TextUtils.isEmpty(budgetValue.getText().toString())) {
                        Toast.makeText(getActivity(), "Please input the budget value", Toast.LENGTH_SHORT).show();
                    } else {
                        //Creates a budget object then calls the Firebase function to create a new budget.
                        value = Integer.parseInt( budgetValue.getText().toString());
                        savedBudget budget = new savedBudget(name, Integer.toString(value));
                        showToast();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Budget").child(name);
                        myRef.setValue(budget);
                    }
                }
            }
        });

        return view;
    }

    //Notifies the user that the budget has been successfully created.
    public void showToast() {
        Toast.makeText(getActivity(), "Your budget has been saved", Toast.LENGTH_SHORT).show();
    }
}