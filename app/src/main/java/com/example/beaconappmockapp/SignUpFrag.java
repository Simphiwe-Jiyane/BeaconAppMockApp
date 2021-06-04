package com.example.beaconappmockapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFrag extends Fragment {

    private static final String TAG = "STATUS ==> ";

    //Input variable
    TextInputLayout tvFirstName, tvSurname, tvEmail, tvPassword, tvConfirm_password;
    Button btnSignUp;

    //stored variables
    String firstname, surname, email, password;

    //Firebase variable
    FirebaseAuth firebaseAuth;

    View view;

    //Progressbar variable
    private ProgressBar spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sign_up_frag, container, false);

        //Initialize firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //Initialize inputs
        tvFirstName = view.findViewById(R.id.firstname);
        tvSurname = view.findViewById(R.id.surname);
        tvEmail = view.findViewById(R.id.email);
        tvPassword = view.findViewById(R.id.password);
        tvConfirm_password = view.findViewById(R.id.passwordConfirm);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        //Initialize ProgressBar
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);

        //Set button on click listener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Spinner Visible
                spinner.setVisibility(View.VISIBLE);

                System.out.println("Clicked ==> Is at 59");
                //TODO: Validate inputs
                //TODO: Confirm password

                firstname = tvFirstName.getEditText().getText().toString();
                System.out.println("Name ==> "+firstname);
                surname = tvSurname.getEditText().getText().toString();
                email = tvEmail.getEditText().getText().toString();
                password = tvPassword.getEditText().getText().toString();

                //TODO: Add user to database


                //Create a user using firebase createWithEmailAndPassword method
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    //Log user in

                                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                                    startActivity(intent);


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //TODO: Output error messages
                                    ShowErrorMessages(null);
                                }
                            }
                        });
                //Spinner Invisible
                spinner.setVisibility(View.GONE);

            }
        });

        return view;
    }

    private void ShowErrorMessages(FirebaseUser user){
        //TODO: Show error messages

    }
}
