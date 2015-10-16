package ch.hsr.mge.gadgeothek;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Reservation;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class ReservationFragment extends Fragment {
    private static ReservationAdapter adapter;
    private TextView noData;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_reservation, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.res_recyclerView);
        noData = (TextView)view.findViewById(R.id.res_no_data);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        LibraryService.getReservationsForCustomer(getReservationsCallback());
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.res_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new NewReservationFragment())
                        .addToBackStack("reservation")
                        .commit();
            }
        });
        return view;
    }
    public void refresh(){
        LibraryService.getReservationsForCustomer(getReservationsCallback());
    }

    private Callback<List<Reservation>> getReservationsCallback(){
        return new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                if (!input.isEmpty()) {
                    adapter = new ReservationAdapter(input);
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
                Toast.makeText(getActivity(),
                        "An error occured while gathering data.\n" + message,
                        Toast.LENGTH_LONG).show();
            }
        };
    }
}
