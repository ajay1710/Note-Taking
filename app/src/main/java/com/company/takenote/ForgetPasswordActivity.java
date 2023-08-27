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
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText mail;
    Button forgetPassword;
    ProgressBar progressBar;

    FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_password);

        mail=findViewById(R.id.editTextRestEmail);
        forgetPassword=findViewById(R.id.buttonResetPassword);
        progressBar=findViewById(R.id.progressBarReset);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMail=mail.getText().toString();
                resetPassword(userMail);
            }
        });
    }

    public void resetPassword(String userMail){
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(userMail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this,"We sent you an email to reset you password",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ForgetPasswordActivity.this,"Sorry, there is a problem, please try again later...",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);


                }
            }
        });
    }
}