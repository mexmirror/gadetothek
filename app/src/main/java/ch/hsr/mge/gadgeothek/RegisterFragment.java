package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
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
        Button registerButton = (Button)view.findViewById(R.id.reg_register);
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String studentId = ((TextView)view.findViewById(R.id.reg_student_id)).getText().toString();
                String mail = ((TextView)view.findViewById(R.id.reg_email)).getText().toString();
                String password = ((TextView)view.findViewById(R.id.reg_password)).getText().toString();
                String name = ((TextView)view.findViewById(R.id.reg_name)).getText().toString();
                LibraryService.register(mail, password, name, studentId, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if(input){
                            getFragmentManager().beginTransaction().replace(R.id.content, new LoginFragment()).addToBackStack("login").commit();
                        } else {
                            Toast.makeText(getActivity(), "Registration failed. Please try again later", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getActivity(), "Error during register process\n" + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
