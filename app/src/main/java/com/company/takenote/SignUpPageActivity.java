package com.company.takenote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPageActivity extends AppCompatActivity {

    EditText mail,password;
    Button signUp;
    ProgressBar progressBar;

    FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        mail=findViewById(R.id.editTextSignUpEmail);
        password=findViewById(R.id.editTextSignUpPassword);
        signUp=findViewById(R.id.buttonSignUp);
        progressBar=findViewById(R.id.progressBarSignUp);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp.setClickable(false);

                String userMail=mail.getText().toString();
                String userPassword=password.getText().toString();
                signUpFirebase(userMail,userPassword);

            }
        });
    }

    public void signUpFirebase(String userMail,String userPassword){
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(userMail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpPageActivity.this, "Your account is created", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            finish();
                        } else {

                            Toast.makeText(SignUpPageActivity.this, "There is a problem, please try again later", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}