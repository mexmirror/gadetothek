package ch.hsr.mge.gadgeothek;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class LoginFragment extends Fragment {


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LibraryService.setServerAddress("10.0.2.2:8080");
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = (Button)view.findViewById(R.id.login_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = ((EditText) view.findViewById(R.id.login_email)).getText().toString();
                String password = ((EditText) view.findViewById(R.id.login_password)).getText().toString();
                LibraryService.login(mail, password, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            Log.d("Login", "success");
                        } else {
                            Log.d("Login", "failed");
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getActivity(), "Error during login process", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        Button registerButton = (Button)view.findViewById(R.id.login_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new RegisterFragment())
                        .commit();
            }
        });
        return view;
    }
}
