"use strict";

let canvases = document.getElementsByTagName("canvas"); // getElementsByTagName always puts things in []
let canvas = canvases[0];
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;
// or let canvases = document.getElementById if it has one

let bee = {}; // object
bee.pos = {};
bee.pos.x = 0;
bee.pos.y = 0;
bee.img = new Image();
bee.img.src = "bee.jpg"; // 50 * 50

let bees = []; // array of bees
for (let i = 0; i < 5; i++) {
    bees[i] = bee;
}

let context = canvas.getContext('2d');
let cWidth = canvas.width;
let cHeight = canvas.height;

// this move with the mouse
let myImg = new Image(); // very similar to <img src="*.jpg"> in HTML
myImg.src = "bee.jpg";
myImg.xPos = 10;
myImg.yPos = 50;

let myBeeImg = new Image();
myBeeImg.src = "bee.jpg";
myBeeImg.xPos = 10;
myBeeImg.yPos = 10;

// function handleMoveMouse (e) {
//     honeyPot.x = e.x;
//     honeyPot.y = e.y;
//     // better to draw in in the main (animation frame code)
// }

function main() {
    //erase
    //draw honey
    //for each bee
    //    draw bee
    //    update bee location
    //    requestAnitiaonadsfa;f

    // context.drawImage(myImg, 0, 100); // 2th and 3th par are x, y locations
	// context.lineWidth = 5;
	// context.strokeRect(20, 20, myImg.width, myImg.height) // draw a border around the img
	window.requestAnimationFrame(animate); // start requesting
}
window.onload = main;

// let xPos = 0; // don't do globle vars
// let yPos = 50;
let goingRight = true;

function animate() { // make it move
	erase();
	context.drawImage(myBeeImg, myBeeImg.xPos, myBeeImg.yPos); // 2th and 3th par are x, y locations
	// context.lineWidth = 5;
	// context.strokeRect(x, y, myBeeImg.width, myBeeImg.height) // draw a border around the img
	if (goingRight) {
		myBeeImg.xPos += 5; // this is actually the speed
	} else {
		myBeeImg.xPos -= 5;
	}
	if (myBeeImg.xPos > cWidth) {
		goingRight = false;
	} else if (myBeeImg.xPos <= 0) {
		goingRight = true;
	}
	window.requestAnimationFrame(animate); // keeps requesting at 60 Hz speed
}

function erase() {
	context.fillStyle = "#AAAAAA"; // cover the screen with same color (like erase)
    // all A: gray, all F: white, all 0: black
	context.fillRect(0, 0, cWidth, cHeight);
}

function handleMove(e) { // follow the mouse
    erase();
    context.drawImage(myBeeImg, myBeeImg.xPos, myBeeImg.yPos);
	context.drawImage(myImg, e.x, e.y); // 2th and 3th par are x, y locations
	// context.lineWidth = 5;
	context.strokeRect(e.x, e.y, myImg.width, myImg.height) // draw a border around the img
}
document.onmousemove = handleMove;
// document.addEventListener("mousemove", handleMove)
//  document.ondblclick = (e) => {draw(e);}; // also works