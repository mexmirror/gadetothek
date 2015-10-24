package ch.hsr.mge.gadgeothek;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.HomeItem;


public class HomeFragment extends Fragment {
    private static HomeAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Home");
        appCompatActivity.getSupportActionBar().show();

        recyclerView = (RecyclerView)view.findViewById(R.id.home_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(initializeFragment());
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(view, "Dismissed", Snackbar.LENGTH_LONG).show();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private List<HomeItem> initializeFragment(){
        List<HomeItem> list = new ArrayList<>(3);
        list.add(new HomeItem(getString(R.string.home_item_3_title), getString(R.string.home_item_3_desc)));
        list.add(new HomeItem(getString(R.string.home_item_2_title), getString(R.string.home_item_2_desc)));
        list.add(new HomeItem(getString(R.string.home_item_1_title), getString(R.string.home_item_1_desc)));
        return list;
    }
}
