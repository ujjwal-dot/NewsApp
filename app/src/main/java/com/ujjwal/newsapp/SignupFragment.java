package com.ujjwal.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://newsapp-d3953-default-rtdb.firebaseio.com/");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.signup_tab_fragment,container,false);

        final EditText name=view.findViewById(R.id.name);
        final EditText email=view.findViewById(R.id.Email);
        final EditText number=view.findViewById(R.id.number);
        final EditText password=view.findViewById(R.id.password);




        final Button rgButton=view.findViewById(R.id.Register);

        rgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mName=name.getText().toString();
                final String mEmail=email.getText().toString();
                final String mNumber=number.getText().toString();
                final String mPassword=password.getText().toString();

                final String mail = mEmail.replace(".","");


                if(mName.isEmpty()||mEmail.isEmpty()||mNumber.isEmpty()||mPassword.isEmpty()){
                    Toast.makeText(getContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else if(mNumber.length()!=10){
                    Toast.makeText(getContext(), "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(mail)){
                                Toast.makeText(getContext(), "Email Already Registered", Toast.LENGTH_SHORT).show();
                            }else{
                                databaseReference.child("users").child(mail).child("Password").setValue(mPassword);
                                databaseReference.child("users").child(mail).child("Name").setValue(mName);
                                databaseReference.child("users").child(mail).child("Number").setValue(mNumber);

                                Toast.makeText(getContext(), "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
}
