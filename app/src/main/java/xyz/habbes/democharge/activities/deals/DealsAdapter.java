package xyz.habbes.democharge.activities.deals;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.habbes.democharge.R;

/**
 * Created by Habbes on 12/10/2016.
 */
class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    private String[] dataset;

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deals_list_item, parent, false);
        DealsViewHolder viewHolder = new DealsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DealsViewHolder holder, int position) {
        holder.textView.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class DealsViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textView;
        public DealsViewHolder(CardView v){
            super(v);
            textView = (TextView) v.findViewById(R.id.dealsCardText);
        }
    }

    public DealsAdapter(String[] d){
        dataset = d;
    }


}