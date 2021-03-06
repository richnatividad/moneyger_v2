package com.moneyger_v2;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//This fragment is for handling all of the status functions (show budget, show expense, show reminder).
public class StatusFragment extends Fragment {
    View view;
    TextView text;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button showBudget;
    Button showExpense;
    Button showReminders;
    String result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_status, container, false);
        text = view.findViewById(R.id.textView);

        ///An onclick event for the show budget. Shows all the created budgets when clicked.
        showBudget = (Button) view.findViewById(R.id.selectBudget);
        showBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Budget");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> td = (HashMap<String, Object>) snapshot.getValue();
                        text.setText(td.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Failed to get data.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //An onclick event for the show expense. Shows all the created expenses when clicked.
        showExpense = (Button) view.findViewById(R.id.selectBudget2);
        showExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Expenses");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> td = (HashMap<String, Object>) snapshot.getValue();
                        text.setText(td.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Failed to get data.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //An onclick event for the show reminder. Shows all the created reminders when clicked.
        showReminders = (Button) view.findViewById(R.id.selectBudget3);
        showReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Reminders");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> td = (HashMap<String, Object>) snapshot.getValue();
                        text.setText(td.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Failed to get data.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        return view;
    }
}