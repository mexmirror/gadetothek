package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class RegisterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        Button registerButton = (Button)view.findViewById(R.id.reg_register);
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String studentId = ((TextView)view.findViewById(R.id.reg_student_id_input)).getText().toString();
                String mail = ((TextView)view.findViewById(R.id.reg_email_input)).getText().toString();
                String password = ((TextView)view.findViewById(R.id.reg_password_input)).getText().toString();
                String name = ((TextView)view.findViewById(R.id.reg_name_input)).getText().toString();
                if(!(studentId.isEmpty() | mail.isEmpty() | password.isEmpty() | name.isEmpty())) {
                    LibraryService.register(mail, password, name, studentId, new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean input) {
                            if (input) {
                                getFragmentManager().beginTransaction().replace(R.id.content, new LoginFragment()).addToBackStack("login").commit();
                            } else {
                                Snackbar.make(view, R.string.register_failed, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Snackbar.make(view, R.string.register_process_failed, Snackbar.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Snackbar.make(view, "Each field is required to register", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
