package com.jazbass.jbtaxis;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView list = findViewById(R.id.list);
        ArrayList<String[]> historyList =
                PrincipalActivity.assistantSQL.getHistory();
        HistoryAdapter historyAdapter = new HistoryAdapter(this, historyList);
        list.setAdapter(historyAdapter);
    }
}
