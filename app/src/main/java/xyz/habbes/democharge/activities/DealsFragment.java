package xyz.habbes.democharge.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.habbes.democharge.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class DealsFragment extends Fragment {

    private RecyclerView dealsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public DealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_deals, container, false);

        dealsRecyclerView = (RecyclerView) view.findViewById(R.id.dealsRecyclerView);
        setUpRecyclerView();

        return view;
    }

    /**
     * initializes the deals recycler view
     * @author: Habbes
     * @added: 12.10.2016
     * @version: 1
     */
    private void setUpRecyclerView(){
        dealsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        dealsRecyclerView.setLayoutManager(layoutManager);
        adapter = new DealsAdapter(new String[]{
                "Pizza: Buy 1 Get 1 Free", "Discounts on Massage", "Awesome"
        });
        dealsRecyclerView.setAdapter(adapter);
    }



}
