"use strict";

let ws = null;
let wsOpen = false;

// <enter> or "click"
function handleKeyPressCB(event) {
    if (event.keyCode == 13) {
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

function handleConnectCB() {
    wsOpen = true;
    let username = usernameTA.value;
    let roomName = roomnameTA.value;
    ws.send(`join ${username} ${roomName}`);
}

// parse the message
function handleMessageFromWsCB(event) {
    let obj = event.data;
    console.log( "just got msg: '" + event.data + "'");
    let myMsgObj = JSON.parse(obj); // turn it into JSON
    console.log(myMsgObj);
    if (myMsgObj.type == "join") {
        document.getElementById("nameList").innerHTML += "<p>" + myMsgObj.username + "</p>" + "</br>";
        document.getElementById("messageList").innerHTML += "<p>" + myMsgObj.username + "joins" + "</p>" + "</br>";
    } else if (myMsgObj.type == "message") {
        document.getElementById("messageList").innerHTML += "<p>" + myMsgObj.username + ": " + myMsgObj.message + "</p>" + "</br>";
    } else if ((myMsgObj).type == "leave") {
        document.getElementById("messageList").innerHTML += "<p>" + myMsgObj.username + ": " + "has left" + "</p>" + "</br>";
    }

    // let realMsg = myMsgObj.message;

    // console.log("real msg: " + realMsg);

    // let msgDiv = document.getElementById("column right");
    // let myPar = document.createElement('p');
    // msgDiv.innerText = realMsg;

    // msgDiv.appendChild(myPar);
}

function addPeople() {
    namelist.innerHTML += "";
}

let usernameTA = document.getElementById("usernameTA");
usernameTA.addEventListener("keypress", handleKeyPressCB);
let roomnameTA = document.getElementById("roomnameTA");
roomnameTA.addEventListener("keypress", handleKeyPressCB);
let namelist = document.getElementById("namelist"); // left panel
let messageTA = document.getElementById("messageTA"); // botton panel
messageTA.addEventListener("keypress", handleKeyPressCB);
let createBtn = document.getElementById("createBtn");
let sendBtn = document.getElementById("sendBtn");

createBtn.addEventListener("click", handleKeyPressCB);
sendBtn.addEventListener("click", handleKeyPressCB);

// let ws = new WebSocket("ws://localhost:8080");
