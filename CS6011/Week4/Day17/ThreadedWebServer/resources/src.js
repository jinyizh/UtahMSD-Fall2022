"use strict";

let ws = null;
let wsOpen = false;

let usernameTA = document.getElementById("usernameTA");
usernameTA.addEventListener("keypress", handleKeyPressCB);

let roomnameTA = document.getElementById("roomnameTA");
roomnameTA.addEventListener("keypress", handleKeyPressCB);

let namelist = document.getElementById("namelist"); // left panel

let messageTA = document.getElementById("messageTA"); // botton panel
messageTA.addEventListener("keypress", handleKeyPressCB);

let createBtn = document.getElementById("createBtn");
createBtn.addEventListener("click", handleKeyPressCB);

let sendBtn = document.getElementById("sendBtn");
sendBtn.addEventListener("click", handleKeyPressCB);

// <enter> or "click"
function handleKeyPressCB(event) {
    if (event.keyCode == 13 || event.type == "click") {
        let username = usernameTA.value;
        let roomName = roomnameTA.value
        let message = messageTA.value;

        event.preventDefault();

        if (username == "" || username == 0 || username == null) {
            alert("Please fill the username correctly");
            usernameTA.select();
            return;
        }

        if (roomName == "" || roomName == 0 || roomName == null) {
            alert("Please fill the room name correctly");
            roomnameTA.select();
            return;
        }

        for (let name of roomName) {
            if (name < 'a' || name > 'z') {
                alert("Room name must consist of lowercase alphabets only!");
                return;
            } 
            // else if (wsOpen) {
            //     alert("Room already created!");
            //     return;
            // }
        }

        if (ws == null) {
            ws = new WebSocket("ws://localhost:8080");
            ws.onopen = handleConnectCB;
            ws.onmessage = handleMessageFromWsCB;
        } else if (wsOpen) {
            ws.send(`${username} ${message}`);
        }
    }
}

function handleMessage(event) {
    console.log(event.data);
}

function handleConnectCB() {
    console.log("WS connected");
    wsOpen = true;
    let username = usernameTA.value;
    let roomName = roomnameTA.value;
    ws.send(`join ${username} ${roomName}`);
}

// parse the message
function handleMessageFromWsCB(event) {
    let obj = event.data;
    let myMsgObj = JSON.parse(obj); // turn it into JSON
    console.log(myMsgObj);
    if (myMsgObj.type == "join") {
        document.getElementById("nameList").innerHTML += "<p>" + myMsgObj.user + "</p>" + "</br>";
        document.getElementById("messageList").innerHTML += "<p>" + myMsgObj.user + " joins" + "</p>" + "</br>";
    } else if (myMsgObj.type == "message") {
        document.getElementById("messageList").innerHTML += "<p>" + myMsgObj.user + ": " + myMsgObj.message + "</p>" + "</br>";
    } else if ((myMsgObj).type == "leave") {
        document.getElementById("messageList").innerHTML += "<p>" + myMsgObj.user + ": " + "has left" + "</p>" + "</br>";
    }
}