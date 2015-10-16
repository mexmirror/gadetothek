package ch.hsr.mge.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;

public class GadgetAdapter extends  RecyclerView.Adapter<GadgetAdapter.ViewHolder>{
    private List<Gadget> gadgetList;
    public GadgetAdapter(List<Gadget> list){
        this.gadgetList = list;
    }
    @Override
    public GadgetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView vendor = (TextView)view.findViewById(R.id.row_vendor);
        TextView available = (TextView)view.findViewById(R.id.row_available);
        TextView product = (TextView)view.findViewById(R.id.row_product);
        TextView price = (TextView)view.findViewById(R.id.row_price);
        ViewHolder viewHolder = new ViewHolder(view, vendor, product, available, price);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GadgetAdapter.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vendor;
        public TextView product;
        public TextView available;
        public TextView price;

        public ViewHolder(View itemView, TextView vendor, TextView product, TextView available, TextView price) {
            super(itemView);
            this.vendor = vendor;
            this.product = product;
            this.available = available;
            this.price = price;

        }
    }
}
