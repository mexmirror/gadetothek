package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class NewReservationFragment extends Fragment {
    private GadgetAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_new_reservation, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.gadget_recyclerView);
        final TextView noData = (TextView)view.findViewById(R.id.gadget_no_data);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                if (!input.isEmpty()) {
                    adapter = new GadgetAdapter(input);
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
                        "An error occured while gathering data\n" + message,
                        Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
