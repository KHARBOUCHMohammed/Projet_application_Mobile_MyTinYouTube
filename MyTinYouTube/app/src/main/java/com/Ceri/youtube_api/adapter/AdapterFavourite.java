package com.Ceri.youtube_api.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Ceri.youtube_api.Database.FavouriteList;
import com.Ceri.youtube_api.R;
import com.Ceri.youtube_api.VideoDetails;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.YoutubHolder> {
    //Creating intial variables
    //This Adapter class is created to handel favourite list recycler view
    private Context context;
    private List<String> videoYTList, thumblist, videoid, title, des, dop;
    private FavouriteList favouriteList;

    public AdapterFavourite(Context context, List<String> videoYTList, List<String> thumblist, List<String> videoid, List<String> title, List<String> des, List<String> dop) {
        //Difing Constructor
        this.context = context;
        this.videoYTList = videoYTList;
        this.thumblist = thumblist;
        this.videoid = videoid;
        this.title = title;
        this.des = des;
        this.dop = dop;
    }


    @NonNull
    @Override
    public AdapterFavourite.YoutubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //Connecting XML layout with java adapter
        View view = inflater.inflate(R.layout.favourite_card_design, parent, false);
        return new YoutubHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavourite.YoutubHolder holder, @SuppressLint("RecyclerView") int position) {

        //Setting up values into variables
        String titleyt = videoYTList.get(position);
        holder.title.setText(titleyt);

        String getThumb = thumblist.get(position);
        //Code for thumbnail
        Picasso.get()
                .load(getThumb)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("Tag", "Tag" + "Sucess");
                    }

                    @Override
                    public void onError(Exception e) {

                        Log.d("Tag", "Tag" + "Error");
                    }
                });


        //Code to remove video from favourite
        holder.removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= 0) {
                    videoYTList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                    favouriteList.delete(title.get(position));
                }


            }
        });
//Basic Information related to video
        //sending from this activity to ViedoDetails activity
        holder.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, VideoDetails.class));
                String vid = videoid.get(position);
                String titleyt = title.get(position);
                String disyt = des.get(position);
                String dopyt = dop.get(position);
                Intent intent = new Intent(context, VideoDetails.class);
                Bundle bundle = new Bundle();
                bundle.putString("vid", vid);
                bundle.putString("title", titleyt);
                bundle.putString("dis", disyt);
                bundle.putString("dop", dopyt);
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });

        //Logic to provide sharable link
        holder.linkshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sharableLink = videoid.get(position);
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "IIT Preparation");
                    String shareMessage = "\n" + "https://www.youtube.com/watch?v=" + sharableLink + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    context.startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoYTList.size();
    }

    public class YoutubHolder extends RecyclerView.ViewHolder {

        //Initialising all variables
        TextView title, linkshare;
        ImageView thumbnail;
        TextView removebtn;
        LinearLayout information;


        public YoutubHolder(@NonNull View itemView) {
            super(itemView);
            favouriteList = new FavouriteList(context);
            title = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumnain_image);
            removebtn = itemView.findViewById(R.id.remove_btn);
            information = itemView.findViewById(R.id.information_video);
            linkshare = itemView.findViewById(R.id.shareelink);


        }


    }
}
