package ch.hsr.mge.gadgeothek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_login);
        LibraryService.setServerAddress("localhost:8080");
        Button loginButton = (Button)findViewById(R.id.login_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = ((EditText)findViewById(R.id.login_email)).getText().toString();
                String password = ((EditText)findViewById(R.id.login_password)).getText().toString();
                LibraryService.login(mail, password, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if(input) {

                        } else {
                            onError("Username or password not found.\n You may want to register first");
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Context context = getApplicationContext();
                        CharSequence text = "Something went wrong. Please contact a developer. \n Error message is: " + message;
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        ((EditText) findViewById(R.id.login_password)).setText("");
                        toast.show();
                    }
                });
            }
        });*/
    }
}
