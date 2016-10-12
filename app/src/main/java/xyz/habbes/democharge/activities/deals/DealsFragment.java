package xyz.habbes.democharge.activities.deals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.habbes.democharge.R;
import xyz.habbes.democharge.activities.helpers.ToastMessage;
import xyz.habbes.democharge.core.models.Deal;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class DealsFragment extends Fragment {

    private RecyclerView dealsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DealsAdapter adapter;

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
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    private void setUpRecyclerView(){
        dealsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        dealsRecyclerView.setLayoutManager(layoutManager);
        adapter = new DealsAdapter();
        dealsRecyclerView.setAdapter(adapter);
    }


    /**
     * fetches deals and populates the recycler view
     * @author: Habbes
     * @added: 12.10.2016
     * @version: 1
     */
    private void loadDeals(){
        Deal.fetch().enqueue(new Callback<List<Deal>>() {

            @Override
            public void onResponse(Call<List<Deal>> call, Response<List<Deal>> response) {
                List<Deal> deals = response.body();
                if(deals.size() == 0){
                    ToastMessage.show(getContext(), getResources().getString(R.string.no_deals_found));
                }
                adapter.setDataset(deals);
            }

            @Override
            public void onFailure(Call<List<Deal>> call, Throwable t) {
                ToastMessage.showError(getContext(), t.getMessage());
            }
        });
    }


}
