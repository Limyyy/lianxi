package lqy.bwie.com.lianxi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import lqy.bwie.com.lianxi.DetailsActivity;
import lqy.bwie.com.lianxi.R;
import lqy.bwie.com.lianxi.bean.Datas;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private Datas datas;

    private OnImageClick onImageClick;
    public interface OnImageClick{
        void getImageClick(View view);
    }

    public MyAdapter(Context context, Datas datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTvName.setText(datas.getResult().get(position).getCommodityName());
        holder.mTvPrice.setText(datas.getResult().get(position).getPrice()+"");
        holder.simple.setImageURI(datas.getResult().get(position).getMasterPic());
        holder.mTvShou.setText("已售"+datas.getResult().get(position).getSaleNum()+"件");
        holder.simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClick.getImageClick(view);
                Intent intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.getResult().size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder{

        private final SimpleDraweeView simple;
        private final TextView mTvName,mTvPrice,mTvShou;

        public MyViewHolder(View itemView) {
            super(itemView);
            simple  = itemView.findViewById(R.id.simple);
            mTvName = itemView.findViewById(R.id.tv_item_name);
            mTvPrice = itemView.findViewById(R.id.tv_item_price);
            mTvShou = itemView.findViewById(R.id.tv_item_shou);
        }
    }
}
