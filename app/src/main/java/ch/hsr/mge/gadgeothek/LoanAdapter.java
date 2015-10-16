package ch.hsr.mge.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Loan;

public class LoanAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Loan> loanList;
    public LoanAdapter(List<Loan> list){
        this.loanList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView vendor = (TextView)view.findViewById(R.id.row_vendor);
        TextView available = (TextView)view.findViewById(R.id.row_available);
        TextView product = (TextView)view.findViewById(R.id.row_product);
        TextView price = (TextView)view.findViewById(R.id.row_price);
        ViewHolder viewHolder = new ViewHolder(view, vendor, product, available, price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Loan loan = loanList.get(position);
        holder.vendor.setText(loan.getGadget().getManufacturer());
        holder.product.setText(loan.getGadget().getName());
        String formattedDate = new SimpleDateFormat("dd.MM.yy").format(loan.getPickupDate());
        holder.available.setText("To: " + formattedDate);
        holder.price.setText(String.valueOf(loan.getGadget().getPrice()) + " CHF");
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

    public void add(int position, Loan loan){
        loanList.add(position, loan);
        notifyItemInserted(position);
    }
    public void remove(int position, Loan loan){
        loanList.remove(loan);
        notifyItemRemoved(position);
    }
}
