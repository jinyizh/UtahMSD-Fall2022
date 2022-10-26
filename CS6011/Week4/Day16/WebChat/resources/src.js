// “use strict”;
// let wsResultTA = document.getElementById(“wsResultTA”);
// let userName = document.getElementById(“uname”);
// let roomName = document.getElementById(“rname”);
// let leftmessage = document.getElementById(“leftmessage”);
// let rightmessage = document.getElementById(“rightmessage”);
// let messageInput = document.getElementById(“messageInput”);
// //Callback Functions
// function handleAjaxErroCB(){
//     console.log(“An Ajax error occured”);
// }
// function handleAjaxSuccessCB(){
//     //this
//     console.log(“got response from the server”);
//     // resultTA.value = this.responseText;
// }
// let user = userName.value;
// let room = roomName.value;
// // Enter or Click Button
// function handleKeyPressCB(event){
//     let message = messageInput.value;
//     if (event.keyCode == 13) { // enter key
//         if (user == “” || user == 0 || user == null) {
//             alert(“Please fill in the User Name”);
//             userName.select();
//             event.preventDefault();
//             return;
//         }
//         // // Here, you need logic to check if the entered username is an existing user name
//         // if(user==userName){
//         // }else{
//         //     user=userName.value;
//         // }
//         // // If it is, don’t do anything different
//         // // If it is new, then update the user variable.
//         if (room == “” || room == 0 || room == null) {
//             alert(“Please fill in the Room Name”);
//             roomName.select();
//             event.preventDefault();
//             return;
//         }
//         if(wsOpen){
//             // ws.send(`join ${user} ${room}`);
//             ws.send(`${user} ${message}`);
//         } else {
//             wsResultTA.value = “WS is not open”;
//         }
//     }
// }
// userName.addEventListener(“keypress”, handleKeyPressCB);
// roomName.addEventListener(“keypress”, handleKeyPressCB);
// messageInput.addEventListener(“keypress”, handleKeyPressCB);
// //////Web Sockets
// let wsOpen = false;
// function handleConnectCB(){
//     wsOpen = true;
//     // ws.send(`join ${user} ${room}`);
// }
// function handleMessageFromWsCB(event){
//     wsResultTA.value = event.data;
//     // console.log(wsResultTA);
//     //parse the message
//     let obj = event.data;
//     let mymsgObj = JSON.parse(obj);
//     console.log(mymsgObj);
//     // ///
//     // //let pieces = msg.split(” “)
//     // let realMsg = mymsgObj.message;
//     // let msgDiv = document.getElementById(“myMsgDiv”);
//     // let myPar = document.createElement(“p”);
//     // myPar.innerText = realMsg;
//     // msgDiv.appendChild(mypar);
//     // ////
//     if(mymsgObj.type == “join”){
//         // document.getElementById(“leftmessage”).innerHTML =mymsgObj.user + ‘</br>‘;
//         document.getElementById(“leftmessage”).innerHTML += ‘<p>’ + mymsgObj.user + ‘</p>’ + ‘</br>‘;
//         document.getElementById(“rightmessage”).innerHTML += ‘<p>’ + mymsgObj.user + ” has joined the chatroom. ” +‘</p>’ + ‘</br>‘;
//     } else if(mymsgObj.type == “message”){
//         // document.getElementById(“rightmessage”).innerHTML =mymsgObj.message+ ‘</br>‘;
//         document.getElementById(“rightmessage”).innerHTML += ‘<p>’ + mymsgObj.user + “: ” + mymsgObj.message + ‘</p>’ + ‘</br>‘;
//     } else if(mymsgObj.type == “leave”){
//         document.getElementById(“rightmessage”).innerHTML += ‘<p>’ + mymsgObj.user +  “left the room.” +‘</p>’ + ‘</br>’;
//     }
// }
// let ws = new WebSocket(“ws://localhost:8080”);
// ws.onopen = handleConnectCB;
// ws.onmessage = handleMessageFromWsCB;

"use strict";

let wsOpen = false;

function handleConnectCB(event) {
    wsOpen = true;
}

function handleMessageFromWsCB(event) {
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

function handleCreateBtnCB(event) {
    console.log("key pressed");
    let sUsername = usernameTA.value;
    if (sUsername == "") {
        alert ("Please enter username!");
        usernameTA.value = "enter username";
        usernameTA.select();
    }
}

function handleSendBtnCB(event) {
    console.log("key pressed");
}

function addPeople() {
    namelist.innerHTML += "";
}

let usernameTA = document.getElementById("usernameTA");
let roomnameTA = document.getElementById("roomnameTA");
let messageTA = document.getElementById("messageTA");
let createBtn = document.getElementById("createBtn");
let sendBtn = document.getElementById("sendBtn");

let namelist = document.getElementById("namelist");

createBtn.addEventListener("click", handleCreateBtnCB);
sendBtn.addEventListener("click", handleSendBtnCB);

let ws = new WebSocket("ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB;