package com.example.pandemictracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class viewAdapter extends RecyclerView.Adapter<viewAdapter.viewHolding> {
    private ArrayList <currencyModel> currencyModelArrayList;
    private Context ctx;
    private DecimalFormat df = new DecimalFormat("#.##");

    public viewAdapter(ArrayList<currencyModel> currencyModelArrayList, Context ctx) {
        this.currencyModelArrayList = currencyModelArrayList;
        this.ctx = ctx;
    }
    public void filterList(ArrayList<currencyModel> filterd){
        currencyModelArrayList = filterd;
        notifyDataSetChanged();
    }
    @NonNull
    @NotNull
    @Override
    public viewAdapter.viewHolding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_layout, parent, false);
        return new viewAdapter.viewHolding(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewAdapter.viewHolding holder, int position) {
        currencyModel currencyModel = currencyModelArrayList.get(position);
        holder.currency_name.setText(currencyModel.getName());
        holder.currency_symb.setText(currencyModel.getSymb());
        holder.currency_rate.setText("$ "+df.format(currencyModel.getCurrency()));
        holder.volume.setText("volume = $ "+currencyModel.getVolume());
        holder.volume_change_24h.setText("changes volume(24hours) = $ "+currencyModel.getVolume_change_24h());
        holder.total_supply.setText("total supply = "+currencyModel.getTotal_supply() + " units");
        holder.circulating_supply.setText("circulating supply = "+currencyModel.getCirculating_supply() + " units");
        holder.percent_change_7d.setText("changed in percentage(7Days) = "+currencyModel.getPercent_change_7d() + " %");
        holder.max_supply.setText("maximum supply = "+currencyModel.getMax_supply()+ " units");
        holder.market_cap.setText("total market capital = $ "+currencyModel.getMarket_cap());

        boolean is_expanded = currencyModelArrayList.get(position).isExpanded();
        holder.expandable_layout.setVisibility(is_expanded ? View.VISIBLE: View.GONE);
    }

    @Override
    public int getItemCount() {
        return currencyModelArrayList.size();
    }

    public class viewHolding extends RecyclerView.ViewHolder {
        private TextView currency_name, currency_symb, currency_rate,
                volume ,volume_change_24h , percent_change_7d , market_cap ,max_supply , circulating_supply ,total_supply;

        RelativeLayout expandable_layout;
        RelativeLayout always_showing;
        public viewHolding(@NonNull @NotNull View itemView) {
            super(itemView);
            currency_name = itemView.findViewById(R.id.currency_name);
            currency_symb = itemView.findViewById(R.id.currency_symb);
            currency_rate = itemView.findViewById(R.id.currency_rate);
            volume = itemView.findViewById(R.id.volume);
            circulating_supply = itemView.findViewById(R.id.circulating_supply);
            volume_change_24h = itemView.findViewById(R.id.volume_change_24h);
            percent_change_7d = itemView.findViewById(R.id.percent_change_7d);
            max_supply = itemView.findViewById(R.id.max_supply);
            volume = itemView.findViewById(R.id.volume);
            total_supply = itemView.findViewById(R.id.total_supply);
            market_cap = itemView.findViewById(R.id.market_cap);

            expandable_layout = itemView.findViewById(R.id.expandable_layout);
            always_showing = itemView.findViewById(R.id.click);

            always_showing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currencyModel model = currencyModelArrayList.get(getAdapterPosition());
                    model.setExpanded(!model.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
