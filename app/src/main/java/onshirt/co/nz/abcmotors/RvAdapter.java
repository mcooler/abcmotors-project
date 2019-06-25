package onshirt.co.nz.abcmotors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModel> dataModelArrayList;


    public RvAdapter(Context ctx, ArrayList<DataModel> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public RvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_one, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RvAdapter.MyViewHolder holder, int position) {

        String path = dataModelArrayList.get(position).getImage();

        if (path != null && path.length() > 0) {
            Picasso.get().load(path).into(holder.iv);
        }else {
            // show no image
        }

        holder.product_id.setText(dataModelArrayList.get(position).getProduct_id());
        holder.model.setText(dataModelArrayList.get(position).getModel());
        holder.price.setText(dataModelArrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView product_id, model, price;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            product_id =  itemView.findViewById(R.id.product_id);
            model =  itemView.findViewById(R.id.model);
            price =  itemView.findViewById(R.id.price);
            iv =  itemView.findViewById(R.id.iv);
        }

    }
}
