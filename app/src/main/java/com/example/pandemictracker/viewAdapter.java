package com.example.pandemictracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.currency_rate.setText("$"+df.format(currencyModel.getCurrency()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolding extends RecyclerView.ViewHolder {
        private TextView currency_name, currency_symb, currency_rate, delta;
        public viewHolding(@NonNull @NotNull View itemView) {
            super(itemView);
            currency_name = itemView.findViewById(R.id.currency_name);
            currency_symb = itemView.findViewById(R.id.currency_symb);
            currency_rate = itemView.findViewById(R.id.currency_rate);
            delta = itemView.findViewById(R.id.currency_delta);

        }
    }
}
