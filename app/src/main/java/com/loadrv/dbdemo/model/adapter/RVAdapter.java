package com.loadrv.dbdemo.model.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loadrv.dbdemo.MainActivity;
import com.loadrv.dbdemo.R;
import com.loadrv.dbdemo.pojo.ChannelList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mc11 on 2/11/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{
    Activity activity;
    List<ChannelList> mchannelList = new ArrayList<>();


    public RVAdapter(MainActivity mainActivity, List<ChannelList> rlist) {
       this. activity = mainActivity;
        this. mchannelList = rlist;

    }

    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, null,false);
        return new MyViewHolder(rview);
    }

    @Override
    public void onBindViewHolder(RVAdapter.MyViewHolder holder, int position) {
        ChannelList currchannel = mchannelList.get(position);
        holder.title.setText(currchannel.getTitle());
        holder.rating.setText(currchannel.getDescription());
        holder.year.setText(currchannel.getDatetime());
       // holder.thumbnail.setText(currchannel.getTitle());
        Picasso.with(holder.itemView.getContext()).load(currchannel.getThumbnailurl()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return mchannelList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        private ImageView thumbnail;
        private TextView title,rating,year,genre;


        public MyViewHolder(View itemView) {
            super(itemView);


            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
             title = (TextView) itemView.findViewById(R.id.title);
             rating = (TextView) itemView.findViewById(R.id.des);
             genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.releaseYear);
        }
    }
}
