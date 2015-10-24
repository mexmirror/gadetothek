package ch.hsr.mge.gadgeothek;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import ch.hsr.mge.gadgeothek.service.LibraryService;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Settings");
        final Spinner spinner = (Spinner)view.findViewById(R.id.server_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    final String msg = spinner.getSelectedItem().toString()  + " " + getString(R.string.not_available);
                    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
                    spinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar.make(view, "At least something must be selected", Snackbar.LENGTH_LONG).show();
            }
        });
        setSpinnerContent(view);
        Button connect = (Button)view.findViewById(R.id.settings_connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.setServerAddress("http://mge1.dev.ifs.hsr.ch/public");
                Snackbar.make(v, "Connected to Hochschule Rapperswil", Snackbar.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void setSpinnerContent(View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.server_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.server_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
