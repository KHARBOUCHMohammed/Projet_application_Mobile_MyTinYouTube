package com.Ceri.youtube_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoDetails extends AppCompatActivity {
    //Creating important variables
    private TextView title, discription, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);

//Initialising toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VideoDetails.this, Mainscreen.class));
            }
        });


//Receiving values by using intent
        Bundle bundle;
        bundle = getIntent().getExtras();
        String vid = bundle.getString("vid");
        String titleyt = bundle.getString("title");
        String dis = bundle.getString("dis");
        String dop = bundle.getString("dop");


        //Initialing all elements
        title = findViewById(R.id.title);
        date = findViewById(R.id.dop);
        discription = findViewById(R.id.description);


        //puting all values in textview
        title.setText(titleyt);
        date.setText(dop);
        discription.setText(dis);

//Logic to play youtube videos
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = vid;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    //bacpress method to go back
    @Override
    public void onBackPressed() {
        startActivity(new Intent(VideoDetails.this, Mainscreen.class));
    }
}