"use strict";

let wsOpen = false;

function handleConnectCB(event) {
    wsOpen = true;
}

function handleMessageFromWsCB(event) {
    console.log("got event", event);
    wsResultTA.value = event.data;
}

function handleAjaxErrorCB() {
    console.log("an AJAX error occured");
    resultTA.value = "Server has a problem";
}

function handleAjaxSuccessCB() {
    // "this" is the object that calls this function
    console.log("got a response from the s");
    resultTA.value = this.responseText;
}

// for enter or add btn
function handleKeyPressCB(event) {
    console.log(event.keyCode);
    if (event.type == "click" || event.keyCode == 13) { // enter key
        let x = parseFloat(xTA.value);
        if (isNaN(x)) {
            alert("Please make sure x is a number");
            xTA.value = "<Enter a number>";
            xTA.select();
            event.preventDefault();
            return;
        }
        let y = parseFloat(yTA.value);
        if (isNaN(y)) {
            alert("Please make sure y is a number");
            yTA.value = "<Enter a number>";
            yTA.select();
            event.preventDefault();
            return;
        }
        let request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y);
        request.addEventListener("error", handleAjaxErrorCB);
        request.addEventListener("load", handleAjaxSuccessCB);
        request.send();
        // resultTA.value = (x + y);

        // make ws request to get the cal
        if (wsOpen) {
            // the server is expecting something like "123 321"
            ws.send(x + " " + y);
        } else {
            wsResultTA.value = "WS is not open...";
        }
    }
}

let xTA = document.getElementById("xTA");
let yTA = document.getElementById("yTA");
let resultTA = document.getElementById("resultTA");
let wsResultTA = document.getElementById("wsResultTA");
let button = document.getElementById("addButton");
xTA.addEventListener("keypress", handleKeyPressCB);
yTA.addEventListener("keypress", handleKeyPressCB);
button.addEventListener("click", handleKeyPressCB);

// create the ws
let ws = new WebSocket("ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB;