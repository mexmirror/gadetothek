package ch.hsr.mge.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;

public class GadgetAdapter extends  RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder>{
    private List<Gadget> gadgetList;
    private View.OnClickListener listener;
    public GadgetAdapter(List<Gadget> list, View.OnClickListener listener){
        this.gadgetList = list;
        this.listener = listener;
    }
    @Override
    public GadgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView vendor = (TextView)view.findViewById(R.id.row_vendor);
        TextView available = (TextView)view.findViewById(R.id.row_available);
        TextView product = (TextView)view.findViewById(R.id.row_product);
        TextView price = (TextView)view.findViewById(R.id.row_price);
        return new GadgetViewHolder(view, vendor, product, available, price);
    }

    @Override
    public void onBindViewHolder(GadgetViewHolder holder, int position) {
        final Gadget gadget = gadgetList.get(position);
        holder.vendor.setText(gadget.getManufacturer());
        holder.product.setText(gadget.getName());
        holder.available.setText("Condition: " + gadget.getCondition().toString());
        holder.price.setText(String.valueOf(gadget.getPrice()) + " CHF");
    }

    @Override
    public int getItemCount() {
        return gadgetList.size();
    }

    public class GadgetViewHolder extends ViewHolder implements View.OnClickListener {

        public GadgetViewHolder(View itemView, TextView vendor, TextView product, TextView available, TextView price) {
            super(itemView, vendor, product, available, price);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v);
        }
    }
}
