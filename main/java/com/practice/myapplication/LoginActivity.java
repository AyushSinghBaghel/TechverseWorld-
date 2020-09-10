package com.practice.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import com.practice.myapplication.UserEntity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Pass;
    private TextView Forgot;
    private TextView Register;
    private Button Login;
    //  private FirebaseAuth  firebaseAuth ;
    private ProgressDialog progressDialog;
    int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpView();

        //For Registering a new User
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating Entity
                final String userEmail = Email.getText().toString();
                final String userPass = Pass.getText().toString();
                if (userEmail.isEmpty() || userPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), " Fill All Fields !!", Toast.LENGTH_SHORT).show();
                } else {
                    //perform query
                    MyAppDatabase database = MyAppDatabase.getMyAppDatabase(getApplicationContext());
                    final UserDAO userDAO = database.userDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDAO.Login(userEmail, userPass);
                            if (userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getApplicationContext(), " Invalid Credential ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                            }
                        }
                    }).start();
                }
            }
        });

    }

    private Boolean validate(UserEntity UserEntity) {
        if (UserEntity.getEmail().isEmpty() ||
                UserEntity.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    private void validateLogin(UserEntity UserEntity) {

        progressDialog.setMessage("Processing Your Request");
        progressDialog.show();

        if (UserEntity.getEmail().isEmpty() || UserEntity.getPassword().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            progressDialog.show();
        } else {
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            counter--;
            progressDialog.dismiss();
            if (counter == 0) {
                Login.setEnabled(false);
                Toast.makeText(LoginActivity.this, "com.practice.myapplication.LoginActivity.User Not Found!! Please Register!!", Toast.LENGTH_SHORT).show();

            }

        }
    }
        private void setUpView () {
            Email = (EditText) findViewById(R.id.email);
            Pass = (EditText) findViewById(R.id.pass);
            Forgot = (TextView) findViewById(R.id.Forgotpass);
            Login = (Button) findViewById(R.id.login);
            Register = (TextView) findViewById(R.id.register);

        }

 /* public void validate(String userName, String userPassword){

            progressDialog.setMessage("Processing Your Request");
            progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task)
          {
         if(task.isSuccessful()){
             progressDialog.dismiss();
             Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(LoginActivity.this,WelcomeActivity.class));
         }
         else{
             Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            counter--;
             progressDialog.dismiss();
             if(counter==0){
                 Login.setEnabled(false);
                 Toast.makeText(LoginActivity.this, "com.practice.myapplication.LoginActivity.User Not Found!! Please Register!!", Toast.LENGTH_SHORT).show();
             }
         }
          }
      });

    }
*/


}