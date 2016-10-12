package xyz.habbes.democharge.activities.deals;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.habbes.democharge.R;
import xyz.habbes.democharge.core.models.Deal;

/**
 * Created by Habbes on 12/10/2016.
 */
class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    private List<Deal> dataset;

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deals_list_item, parent, false);
        DealsViewHolder viewHolder = new DealsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DealsViewHolder holder, int position) {
        holder.textView.setText(dataset.get(position).name);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DealsViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textView;
        public DealsViewHolder(CardView v){
            super(v);
            textView = (TextView) v.findViewById(R.id.dealsCardText);
        }
    }

    /**
     *
     * @param deals
     */
    public DealsAdapter(List<Deal> deals){
        dataset = deals;
    }

    // default constructor
    public DealsAdapter(){};

    /**
     * sets the data for the adapter
     * @param deals
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    public void setDataset(List<Deal> deals){
        dataset = deals;
    }


}