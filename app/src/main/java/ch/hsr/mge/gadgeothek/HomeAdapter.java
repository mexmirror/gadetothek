package ch.hsr.mge.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>{
    private List<HomeItem> itemList;
    public HomeAdapter(List<HomeItem> list){
        this.itemList = list;
    }

    @Override
    public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.homelayout, parent, false);
        TextView title = (TextView)view.findViewById(R.id.home_title);
        TextView description = (TextView)view.findViewById(R.id.home_description);
        return new HomeItemViewHolder(view, title, description);
    }

    @Override
    public void onBindViewHolder(HomeItemViewHolder holder, int position) {
        final HomeItem item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class HomeItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;

        public HomeItemViewHolder(View itemView, TextView title, TextView description) {
            super(itemView);
            this.title = title;
            this.description = description;
        }
    }
    public HomeItem remove(int position){
        HomeItem item = itemList.get(position);
        itemList.remove(position);
        return item;
    }
}
