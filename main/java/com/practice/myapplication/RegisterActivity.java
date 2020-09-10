package com.practice.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Pass;
    private TextView Forgot;
    private TextView Register;
    private Button Login;
    private ProgressDialog progressDialog;
    int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpView();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating Entity
                final UserEntity userEntity = new UserEntity();
                userEntity.setName(Email.getText().toString());
                userEntity.setPassword(Pass.getText().toString());
                if (validate(userEntity)) {
                    // Insertion Operation
                    MyAppDatabase userDatabase = MyAppDatabase.getMyAppDatabase(getApplicationContext());
                    final UserDAO userDAO = userDatabase.userDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDAO.addUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), " Successfully Registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                }
                            });

                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Fill All The Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });



  /*  private EditText userName, userEmail, userPassword;
    private Button register;
    private TextView backToLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUPUIView();
       firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //uploading data to database
                    String user_email =userEmail.getText().toString().trim();
                    String user_pass  =userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            }else{
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                        }
                    });

                }

            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }
     private void setUPUIView(){
        userName=(EditText)findViewById(R.id.Rname);
         userEmail=(EditText)findViewById(R.id.Remail);
         userPassword=(EditText)findViewById(R.id.Rpass);
         register=(Button)findViewById(R.id.Rbutton);
         backToLogin=(TextView) findViewById(R.id.RbackLogin);
     }

     private boolean validate(){
        boolean result=false;

        String name=userName.getText().toString();
         String pass=userPassword.getText().toString();
         String email=userEmail.getText().toString();
        if(name.isEmpty() || pass.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please Enter All The Deatils", Toast.LENGTH_SHORT).show();
        }else
        {
            result= true;

        }
        return result;
     }*/
}

    private Boolean validate(UserEntity UserEntity) {
        if (UserEntity.getEmail().isEmpty() ||
                UserEntity.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    private void setUpView () {
        Email = (EditText) findViewById(R.id.email);
        Pass = (EditText) findViewById(R.id.pass);
        Forgot = (TextView) findViewById(R.id.Forgotpass);
        Login = (Button) findViewById(R.id.login);
        Register = (TextView) findViewById(R.id.register);

    }


}