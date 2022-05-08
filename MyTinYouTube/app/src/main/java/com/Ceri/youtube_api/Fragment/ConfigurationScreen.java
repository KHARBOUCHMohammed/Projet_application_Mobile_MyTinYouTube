package com.Ceri.youtube_api.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Ceri.youtube_api.Database.Database;
import com.Ceri.youtube_api.Mainscreen;
import com.Ceri.youtube_api.R;

//This is configuration fragment
public class ConfigurationScreen extends Fragment {
    //creating initial variables
    EditText number, min, max;
    Database database;
    Button submt_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configuration_screen_fragement, null);

//initialsing all variables
        number = view.findViewById(R.id.frequency);
        min = view.findViewById(R.id.inbox);
        max = view.findViewById(R.id.inbox1);


        database = new Database(getContext());
        Cursor cursor = database.getAlldata();

        //logic to accept all changes
        submt_btn = view.findViewById(R.id.submtbtn);
        submt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result = number.getText().toString();
                String minduration = min.getText().toString();
                String maxduration = max.getText().toString();

                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                if (cursor.getCount() == 0) {
                    database.insertdata(result, minduration, maxduration);
                   // Toast.makeText(getContext(), "New Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    database.updataData("1", result, minduration, maxduration);
                    //Toast.makeText(getContext(), "Date Updated", Toast.LENGTH_SHORT).show();
                }
//After accepting all changes this logic will refresh the app to apply
                //changes into app
                ((Mainscreen) getActivity()).refreshUI();


            }
        });


        return view;
    }
}
