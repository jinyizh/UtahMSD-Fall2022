package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.androidchatclient.MainActivity.ws;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatViewActivity extends AppCompatActivity {

    public static ListView listView;
    public static ArrayList<String> messages = new ArrayList<>();
    public static ArrayAdapter adapter;

    private String username;
    private String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.usernameExtra);
        roomName = intent.getStringExtra(MainActivity.roomNameExtra);

        TextView textView = findViewById(R.id.welcome);
        textView.setText("Hello, " + username + "! You are in: " + roomName + ".");

        listView = findViewById(R.id.listMessages);
        adapter = new ArrayAdapter(ChatViewActivity.this, android.R.layout.simple_list_item_1, messages);
        listView.setAdapter(adapter);
    }

    public void handleSend(View view) {
        Log.i("Z: ChatViewActivity", "button pressed");
        TextView textView = findViewById(R.id.textInputEditText);
        String msg = textView.getText().toString(); // message to be sent
        ws.sendText(username + " " + msg);
//        messages.add(msg);
    }

    public void handleLeave(View view) {
        Log.i("Z: ChatViewAcrivityy", "button pressed");

        Intent leaveIntent = new Intent(this, MainActivity.class);
        startActivity(leaveIntent);
    }
}