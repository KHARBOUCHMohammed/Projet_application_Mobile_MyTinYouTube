package com.Ceri.youtube_api.Fragment;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Ceri.youtube_api.Database.Database;
import com.Ceri.youtube_api.Database.FavouriteList;
import com.Ceri.youtube_api.Mainscreen;
import com.Ceri.youtube_api.R;
import com.Ceri.youtube_api.adapter.AdapterHome;
import com.Ceri.youtube_api.models.ModelHome;
import com.Ceri.youtube_api.models.VideoYT;
import com.Ceri.youtube_api.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends Fragment {
    //This fragment shows all the Home screen  videos
    //Creating all important variables
    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoYTList = new ArrayList<>();
    private EditText searchresult;
    String querry = "c language course", no_of_videos;
    Button submt;
    FavouriteList db;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen_fragement, null);
        //Setting up recyclerview to show all the favourite videos

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new AdapterHome(getContext(), videoYTList);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        db = new FavouriteList(getContext());

        //setting up database to fetch all values
        database = new Database(getContext());
        Cursor cursor = database.getAlldata();
        if (cursor.getCount() == 0) {
            no_of_videos = "10";
        } else {
//Code to get frequency of videos to show on home page
            //by default frequency is 10
            StringBuffer buffer = new StringBuffer();
            cursor.moveToLast();
            buffer.append(cursor.getInt(1));
            no_of_videos = buffer.toString();
            //Toast.makeText(getContext(), no_of_videos, Toast.LENGTH_SHORT).show();
        }

        submt = view.findViewById(R.id.submit_btn);
        searchresult = view.findViewById(R.id.searchresult);
        if (videoYTList.size() == 0) {
//calling function to get json
            getJson();
        }

//Logic for search button
        submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = videoYTList.size();
                videoYTList.clear();
                adapter.notifyItemRangeRemoved(0, size);

                querry = searchresult.getText().toString();
                if (videoYTList.size() == 0) {
                    getJson();
                }

            }
        });

        //logic to swipe videos
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
//left swipe logic
                case ItemTouchHelper.LEFT:

                    videoYTList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(getContext(), "Successfully Removed", Toast.LENGTH_SHORT).show();
                    break;
                //Right swipe logic
                case ItemTouchHelper.RIGHT:
                    addtofavourite(position);
                    videoYTList.remove(position);
                    adapter.notifyItemRemoved(position);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Notification !");
                    alertDialogBuilder
                            .setMessage("Successfully Added to Favourite list !")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //After Pressing ok video will reflect into favourite
                                            ((Mainscreen) getActivity()).refreshUI();
                                        }
                                    });


                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    break;
            }


        }

        //logic to change color while swiping
        //red color to delate video
        //green color to add value into favourite list
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            final int DIRECTION_RIGHT = 1;
            final int DIRECTION_LEFT = 0;

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive) {
                int direction = dX > 0 ? DIRECTION_RIGHT : DIRECTION_LEFT;
                int absoluteDisplacement = Math.abs((int) dX);
                switch (direction) {
                    case DIRECTION_RIGHT:
                        View itemview = viewHolder.itemView;
                        ColorDrawable background = new ColorDrawable();
                        background.setColor(Color.parseColor("#08ff00"));
                        background.setBounds(itemview.getLeft(), itemview.getTop(), itemview.getRight(), itemview.getBottom());
                        background.draw(c);


                        break;

                    case DIRECTION_LEFT:
                        View itemview1 = viewHolder.itemView;
                        ColorDrawable background1 = new ColorDrawable();
                        background1.setColor(Color.parseColor("#ff0034"));
                        background1.setBounds(itemview1.getLeft(), itemview1.getTop(), itemview1.getRight(), itemview1.getBottom());
                        background1.draw(c);
                        break;
                }
            }

        }
    };

    //function to add video into favourite list
    private void addtofavourite(int position) {

        VideoYT getid = videoYTList.get(position);
        String videoid = getid.getId().getVideoId();
        String getThumb = getid.getSnippet().getThumbnails().getMedium().getUrl();
        String title = getid.getSnippet().getTitle();
        String des = getid.getSnippet().getDescription();
        String dop = getid.getSnippet().getPublishedAt();
        Boolean inserrtdata = db.insert(title, getThumb, videoid, title, des, dop);
    }

    //logic to get important data regarding video
    public void getJson() {
        String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBSUgZ8EGi85Llb62LHOpz_GQvpdzo7Xsc&maxResults=" + no_of_videos + "&order=date&q=" + querry + "&type=video&part=snippet";
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if (response.errorBody() != null) {
                    Log.v("Tag", "onResponse:" + response.errorBody());
                } else {
                    ModelHome mh = response.body();
                    videoYTList.addAll(mh.getItems());
                    Log.d("Tag", String.valueOf(videoYTList));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {

            }
        });
    }
}
