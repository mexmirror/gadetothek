package ch.hsr.mge.gadgeothek;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class LoanFragment extends Fragment {
    private LoanAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_loan, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Loan");
        recyclerView = (RecyclerView)view.findViewById(R.id.loan_recyclerView);
        final ViewGroup noData =(ViewGroup)view.findViewById(R.id.loan_no_data);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                if (!input.isEmpty()) {
                    adapter = new LoanAdapter(input);
                    noData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String message) {
                recyclerView.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
                Snackbar.make(view, R.string.data_gather_error, Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
