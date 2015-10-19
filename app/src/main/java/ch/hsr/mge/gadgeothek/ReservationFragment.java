package ch.hsr.mge.gadgeothek;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    private ViewGroup noData;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_reservation, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Reservation");
        appCompatActivity.getSupportActionBar().show();


        recyclerView = (RecyclerView)view.findViewById(R.id.res_recyclerView);
        noData = (ViewGroup)view.findViewById(R.id.res_no_data);
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

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int fromPos = viewHolder.getAdapterPosition();
                final Reservation reservation = adapter.remove(fromPos);
                LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if(input){
                            adapter.notifyItemRemoved(fromPos);
                            Snackbar.make(view, "Deleted " + reservation.getGadget().getName(), Snackbar.LENGTH_LONG).show();
                            LibraryService.getReservationsForCustomer(getReservationsCallback());
                        } else {
                            // adapter.refresh(); TODO refresh
                            Snackbar.make(view, "Error during server delete process", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getActivity(), "Error during delete process", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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
