package com.example.androidchatclient;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class myWebSocket extends WebSocketAdapter {
    String message = "test msg";

    @Override
    public void onConnected(WebSocket webSocket, Map<String, List<String>> headers) throws Exception {
        Log.i("Z: myWebSocket", "WS Connected");
    }

    @Override
    public void onTextMessage(WebSocket webSocket, String payload) throws Exception {
        Log.i("Z: myWebSocket", "WS Message");

        JSONObject json = new JSONObject(payload);
        String type = (String) json.get("type");
        String user = (String) json.get("user");
        String room = (String) json.get("room");

        String timeStamp = " ";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("HH:mm").format(new java.util.Date());
        }
        if (type.equals("join")) {
            message = user + " has joined " + room + " " + timeStamp;
            Log.i("Z: myWebSocket", message);
        } else if (type.equals("message")) {
            String realMsg = (String) json.get("message");
            message = user + ": " + realMsg + " " + timeStamp;
            Log.i("Z: myWebSocket", message);
        } else {
            message = user + " has left the " + room + " " + timeStamp;
            Log.i("Z: myWebSocket", message);
        }

        ChatViewActivity.messages.add(message);
        ChatViewActivity.listView.post(new Runnable() {
            @Override
            public void run() {
                ChatViewActivity.adapter.notifyDataSetChanged();
                ChatViewActivity.listView.smoothScrollToPosition(ChatViewActivity.adapter.getCount());
            }
        });
    }
}
