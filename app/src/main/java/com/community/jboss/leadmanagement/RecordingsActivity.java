package com.community.jboss.leadmanagement;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.community.jboss.leadmanagement.utils.RecordingsListAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecordingsActivity extends AppCompatActivity {

    private final String AUDIO_RECORDER_FOLDER = "Lead Management Recordings";
    private ArrayList<String> filePaths;
    private ArrayList<String> recordingIDs;
    private ArrayList<String> dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordings);
        ListView lv_recordings = findViewById(R.id.lv_recordings);
        filePaths = new ArrayList<>();
        recordingIDs = new ArrayList<>();
        dates = new ArrayList<>();
        try {
            getData();
        } catch (Exception e){
            e.printStackTrace();
        }
        RecordingsListAdapter adapter = new RecordingsListAdapter(RecordingsActivity.this, recordingIDs, dates, filePaths);
        lv_recordings.setAdapter(adapter);
    }

    private void getData() {
        String filePath = Environment.getExternalStorageDirectory().getPath()+"/"+AUDIO_RECORDER_FOLDER;
        Log.e("FILES P ", filePath);
        try {
            ArrayList<String> list = listToArrayList(new File(filePath).list());
            Log.e("FILES", String.valueOf(list.size()));

            if (list != null){
                for (int i = 0; i < list.size(); ++i){
                    Log.e("FILE:", filePath + "/" + list.get(i));
                }
            }
            filePaths = list;
        } catch (Exception e) {
            Log.v("List error:", "can't list" + filePath);
        }
        for(int i = 0; i< filePaths.size(); i++){
            File file = new File(filePaths.get(i));
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            dates.add(sdf.format(file.lastModified()));
            recordingIDs.add(file.getName());
        }
    }

    private ArrayList<String> listToArrayList(String[] list) {
        ArrayList<String> res = new ArrayList<>();
        for(String s : list){
            res.add(s);
        }
        return res;
    }

}