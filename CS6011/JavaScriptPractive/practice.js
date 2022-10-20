"use strict";

let canvases = document.getElementsByTagName("canvas"); // getElementsByTagName always puts things in []
let canvas = canvases[0];
// or let canvases = document.getElementById if it has one

let bee = {};
bee.pos = {};
bee.pos.x = 0;
bee.pos.y = 0;
bee.img = new Image();
bee.img.src = "dafafaf.png";

let bees = [];


let context = canvas.getContext('2d');
let cWidth = canvas.width;
let cHeight = canvas.height;

let myImg = new Image(); // very similar to <img src="*.jpg"> in HTML
myImg.src = "name.jpg";
myImg.xPos = 10;
myImg.yPos = 50;

function main() {
    // context.drawImage(myImg, 0, 100); // 2th and 3th par are x, y locations
	// context.lineWidth = 5;
	// context.strokeRect(20, 20, myImg.width, myImg.height) // draw a border around the img
	window.requestAnimationFrame(animate); // start requesting
}
window.onload = main;

// let xPos = 0; // don't do globle vars
// let yPos = 50;
let goingRight = true;

function animate() {
	erase();
	context.drawImage(myImg, xPos, yPos); // 2th and 3th par are x, y locations
	// context.lineWidth = 5;
	// context.strokeRect(x, y, myImg.width, myImg.height) // draw a border around the img
	if (goingRight) {
		myImg.xPos += 5; // this is actually the speed
	} else {
		myImg.xPos -= 5;
	}
	if (myImg.xPos > cWidth) {
		goingRight = false;
	} else if (myImg.xPos <= 0) {
		goingRight = true;
	}
	window.requestAnimationFrame(animate); // keeps requesting at 60 Hz speed
}

function erase() {
	context.fillStyle = "#AAAAAA"; // cover the screen with all gray (like erase)
	context.fillRect(0, 0, cWidth, cHeight);
}

function handleMove(e) {
	context.drawImage(myImg, e.x, e.y); // 2th and 3th par are x, y locations
	context.lineWidth = 5;
	context.strokeRect(e.x, e.y, myImg.width, myImg.height) // draw a border around the img
}
document.onmousemove = handleMove;
//  document.ondblclick = (e) => {draw(e);}; // also works