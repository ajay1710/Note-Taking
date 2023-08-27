package com.company.takenote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPageActivity extends AppCompatActivity {

    EditText mail,password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp,forgetPassword;
    ProgressBar progressBar;


    FirebaseAuth auth=FirebaseAuth.getInstance();
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);



        mail=findViewById(R.id.editTextTextLoginEmail);
        password=findViewById(R.id.editTextLoginPassword);
        signIn=findViewById(R.id.buttonLoginSignIn);

        signUp=findViewById(R.id.textViewLoginSignup);
        forgetPassword=findViewById(R.id.textViewLoginForgetPassword);
        progressBar=findViewById(R.id.progressBarLoginIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMail=mail.getText().toString();
                String userPassword=password.getText().toString();
                signInFirebase(userMail,userPassword);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginPageActivity.this,SignUpPageActivity.class);
                startActivity(i);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(LoginPageActivity.this,ForgetPasswordActivity.class);
                startActivity(i);

            }
        });
    }







  private void firebaseSignIntWithGoogle(Task<GoogleSignInAccount> task){
      try {
          GoogleSignInAccount account=task.getResult(ApiException.class);
          Toast.makeText(this,"Sucessfully",Toast.LENGTH_SHORT).show();
          Intent i=new Intent(LoginPageActivity.this,MainActivity.class);
          startActivity(i);
          finish();
          firebaseGoogleAccount(account);
      } catch (ApiException e) {
          e.printStackTrace();
          Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();

      }

  }

  private void firebaseGoogleAccount(GoogleSignInAccount account){
      AuthCredential authCredential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
      auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                //  FirebaseUser user=auth.getCurrentUser();
              }else{

              }
          }
      });
  }
    public void signInFirebase(String userMail, String userPassword){
        progressBar.setVisibility(View.VISIBLE);
        signIn.setClickable(false);
        auth.signInWithEmailAndPassword(userMail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(LoginPageActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginPageActivity.this, "Sign In successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginPageActivity.this, "Sign In not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}