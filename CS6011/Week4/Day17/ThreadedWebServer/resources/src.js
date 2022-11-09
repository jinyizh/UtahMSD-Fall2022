ws = null;
wsOpen = false;

function handleConnect() {
    console.log("web socket connected");
}

function handleMessage(event) {
    console.log(event.data);
}

function main() {
    ws = new WebSocket("ws://localhost:8080");
    wsOpen = true;
    ws.onopen = handleConnect;
    ws.onmessage = handleMessage;
}

window.onload = main;