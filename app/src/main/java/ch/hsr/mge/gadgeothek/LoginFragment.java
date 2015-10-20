package ch.hsr.mge.gadgeothek;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class LoginFragment extends Fragment {


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LibraryService.setServerAddress("http://mge1.dev.ifs.hsr.ch/public");
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        Button loginButton = (Button)view.findViewById(R.id.login_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = ((EditText) view.findViewById(R.id.login_email_input)).getText().toString();
                String password = ((EditText) view.findViewById(R.id.login_password_input)).getText().toString();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                if(!(mail.isEmpty() | password.isEmpty())) {
                    LibraryService.login(mail, password, new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean input) {
                            if (input) {
                                getFragmentManager().beginTransaction().replace(R.id.content, new HomeFragment()).commit();
                            } else {
                                Snackbar.make(view, R.string.login_process_failed, Snackbar.LENGTH_LONG).show();
                                ((EditText) view.findViewById(R.id.login_password)).setText("");
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Snackbar.make(view, R.string.login_failed, Snackbar.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Snackbar.make(view, R.string.login_empty, Snackbar.LENGTH_LONG).show();
                }

            }
        });
        Button registerButton = (Button)view.findViewById(R.id.login_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new RegisterFragment())
                        .addToBackStack("login")
                        .commit();
            }
        });
        return view;
    }
}
