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

public class LoginFrag extends Fragment {

    private static final String TAG = "STATUS ==> ";

    //Input variable
    TextInputLayout txtEmail, txtPassword;
    Button btnLogin;

    //stored variables
    String email, password;

    //Firebase variable
    FirebaseAuth firebaseAuth;

    View view;

    //Progressbar variable
    private ProgressBar spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login_frag, container, false);

        //Initialize firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //Initialize inputs
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPassword = view.findViewById(R.id.txtPassword);

        btnLogin = view.findViewById(R.id.btnLogin);

        //Initialize ProgressBar
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);

        //Set button on click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Spinner Visible
                spinner.setVisibility(View.VISIBLE);

                System.out.println("Clicked ==> Is at 59");
                //TODO: Validate inputs
                //TODO: Confirm password

                email = txtEmail.getEditText().getText().toString();
                password = txtPassword.getEditText().getText().toString();

                //TODO: Add user to database


                //Create a user using firebase createWithEmailAndPassword method
                firebaseAuth.signInWithEmailAndPassword(email, password)
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
