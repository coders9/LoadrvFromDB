package com.loadrv.dbdemo.model.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.loadrv.dbdemo.MainActivity;
import com.loadrv.dbdemo.R;
import com.loadrv.dbdemo.model.helper.Utils;
import com.loadrv.dbdemo.pojo.ChannelList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mc11 on 2/11/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{
    Context mcontext;
    List<ChannelList> mchannelList = new ArrayList<>();


    public RVAdapter(MainActivity mainActivity, List<ChannelList> rlist) {
       this. mcontext = mainActivity;
        this. mchannelList = rlist;

    }

    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, null,false);
        return new MyViewHolder(rview);
    }

    @Override
    public void onBindViewHolder(final RVAdapter.MyViewHolder holder, int position) {
        ChannelList currchannel = mchannelList.get(position);
        holder.title.setText(currchannel.getTitle());
        holder.rating.setText(currchannel.getDescription());
        holder.year.setText(currchannel.getDatetime());

       // Picasso.with(mcontext).load(Utils.BitMapToString(currchannel.getThumbnailurl())).into(holder.thumbnail);
//        Log.d("picasso",Utils.BitMapToString(currchannel.getThumbnailurl()));
        /* Glide.with(mcontext)
                .load(currchannel.getThumbnailurl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(100,100) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        holder.thumbnail.setImageBitmap(resource); // Possibly runOnUiThread()
                    }
                });*/
        Glide.with(mcontext)
                .load(currchannel.getThumbnailurl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.thumbnail);
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
