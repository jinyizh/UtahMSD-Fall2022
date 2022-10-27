// "use strict";
// let userName = document.getElementById(“uname”);
// let roomName = document.getElementById(“rname”);
// let leftmessage = document.getElementById(“leftmessage”);
// let rightmessage = document.getElementById(“rightmessage”);
// let messageInput = document.getElementById(“messageInput”);
// // Callback Functions
// function handleAjaxErroCB(){
//     console.log(“An Ajax error occured”);
// }
// function handleAjaxSuccessCB(){
//     //this
//     console.log(“got response from the server”);
//     // resultTA.value = this.responseText;
// }
// let ws = null;
// // Enter or Click Button
// function handleCB(event){
//     if (event.keyCode == 13) { // enter key
//         let user = userName.value;
//         let room = roomName.value;
//         let message = messageInput.value;
//         if (user == “” || user == 0 || user == null) {
//             alert(“Please fill in the User Name”);
//             userName.select();
//             event.preventDefault();
//             return;
//         }
//         if (room == “” || room == 0 || room == null) {
//             alert(“Please fill in the Room Name”);
//             roomName.select();
//             event.preventDefault();
//             return;
//         }
//         for(let list of room){
//             if(list <‘a’ || list > ‘z’){
//                 alert(“Room name must be in lowercase!“)
//                 return;
//             }
//         }
//         if( ws == null ){
//             ws = new WebSocket(“ws://localhost:8080”);
//             ws.onopen = handleConnectCB;
//             ws.onmessage = handleMessageFromWsCB;
//             // ws.send(`join ${user} ${room}`);
//             // ws.send(`${user} ${message}`);
//         }
//         else if(wsOpen){
//             ws.send(`${user} ${message}`);
//         }
//     }
// }
// userName.addEventListener(“keypress”, handleCB);
// roomName.addEventListener(“keypress”, handleCB);
// messageInput.addEventListener(“keypress”, handleCB);
// //////Web Sockets
// let wsOpen = false;
// function handleConnectCB(){
//     wsOpen = true;
//     let user = userName.value;
//     let room = roomName.value;
//     // let message = messageInput.value;
//     ws.send(`join ${user} ${room}`);
//     //ws.send(`${userName.value} ${messageInput.value}`);
// }
// function handleMessageFromWsCB(event){
//     //parse the message
//     let obj = event.data;
//     let mymsgObj = JSON.parse(obj);
//     console.log(mymsgObj);
//     if(mymsgObj.type == “join”){
//         // document.getElementById(“leftmessage”).innerHTML = mymsgObj.user + “</br>“;
//         document.getElementById(“leftmessage”).innerHTML += “<p>” + mymsgObj.user + “</p>” + “</br>“;
//         document.getElementById(“rightmessage”).innerHTML += “<p>” + mymsgObj.user + ” has joined the chatroom. ” +“</p>” + “</br>“;
//     } else if(mymsgObj.type == “message”){
//         // document.getElementById(“rightmessage”).innerHTML = mymsgObj.message+ “</br>“;
//         document.getElementById(“rightmessage”).innerHTML += “<p>” + mymsgObj.user + “: ” + mymsgObj.message + “</p>” + “</br>“;
//     } else if(mymsgObj.type == “leave”){
//         document.getElementById(“rightmessage”).innerHTML += “<p>” + mymsgObj.user +  “left the room.” +“</p>” + “</br>“;
//     }
// }

"use strict";

let ws = null;
let wsOpen = false;

// <enter> or "click"
function handleKeyPressCB(event) {
    if (event.keyCode == 13) {
        let username = usernameTA.value;
        let roomName = roomnameTA.value
        let message = messageTA.value;
        if (username == "" || username == 0 || username == null) {
            alert("Please input the username correctly");
            usernameTA.select();
            event.preventDefault();
            return;
        }
        if (roomName == "" || roomName == 0 || roomName == null) {
            alert("Please input the room name correctly");
            roomnameTA.select();
            event.preventDefault();
            return;
        }
        for (let name of roomName) {
            if (name < 'a' || list > 'z') {
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

function handleConnectCB(event) {
    wsOpen = true;
    let username = usernameTA.value;
    let roomName = roomnameTA.value;
    ws.send(`join ${username} ${roomName}`);
}

// parse the message
function handleMessageFromWsCB(event) {
    let obj = event.data;
    let myMsgObj = JSON.parse(obj);
    console.log(myMsgObj);
    if (myMsgObj.type == "join") {
        
    }




    console.log("got event", event);
    wsResultTA.value = event.data; // string
    // "{type: message, name: 'Davison', room: 'meb3255', message = 'hello how are you?'}" -> things like that

    let jsonObj = JSON.parse(msg);
    let realMsg = jsonObj.message;
    let msgDiv = document.getElementById("column right");
    let myPar = document.createElement('p');
    msgDiv.innerText = realMsg;

    msgDiv.appendChild(myPar);
}

function handleSendBtnCB(event) {
    console.log("key pressed");
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

createBtn.addEventListener("click", handleCreateBtnCB);
sendBtn.addEventListener("click", handleSendBtnCB);

// let ws = new WebSocket("ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB;