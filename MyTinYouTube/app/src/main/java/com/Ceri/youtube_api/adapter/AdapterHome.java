package com.Ceri.youtube_api.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Ceri.youtube_api.R;
import com.Ceri.youtube_api.VideoDetails;
import com.Ceri.youtube_api.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Creating intial variables
    //This Adapter class is created to handel Home Screen list recycler view

    private Context context;
    private List<VideoYT> videoYTList;

    //Creating Constructor
    public AdapterHome(Context context, List<VideoYT> videoYTList) {
        this.context = context;
        this.videoYTList = videoYTList;
    }

    class YoutubHolder extends RecyclerView.ViewHolder {

        ImageView thumbnnail;
        TextView judul;

//Initialising variables
        public YoutubHolder(@NonNull View itemView) {
            super(itemView);
            thumbnnail = itemView.findViewById(R.id.imageView);
            judul = itemView.findViewById(R.id.yttitle);



        }
//setting data into variables
        public void setData(VideoYT videoYT) {
            String getJudul = videoYT.getSnippet().getTitle();
            String getThumb = videoYT.getSnippet().getThumbnails().getMedium().getUrl();
            judul.setText(getJudul);
            Picasso.get()
                    .load(getThumb)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(thumbnnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("Tag", "Tag" + "Sucess");
                        }

                        @Override
                        public void onError(Exception e) {

                            Log.d("Tag", "Tag" + "Error");
                        }
                    });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //Connecting XML layout with java adapter
        View view = inflater.inflate(R.layout.row_item_home, parent, false);
        return new YoutubHolder(view);
    }

    //Basic Information related to video
    //sending from this activity to ViedoDetails activity

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoYT videoYT = videoYTList.get(position);
        YoutubHolder yth = (YoutubHolder) holder;
        yth.setData(videoYT);

        ((YoutubHolder) holder).thumbnnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, VideoDetails.class));
                VideoYT videoYT1 = videoYTList.get(position);
                String vid = videoYT1.getId().getVideoId();
                String titleyt = videoYT1.getSnippet().getTitle();
                String disyt = videoYT1.getSnippet().getDescription();
                String dopyt = videoYT1.getSnippet().getPublishedAt();
                Intent intent = new Intent(context,VideoDetails.class);
                Bundle bundle = new Bundle();
                bundle.putString("vid",vid);
                bundle.putString("title",titleyt);
                bundle.putString("dis",disyt);
                bundle.putString("dop",dopyt);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoYTList.size();
    }
}
