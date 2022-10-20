"use strict";

let canvases = document.getElementsByTagName("canvas");
let canvas = canvases[0];
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

let ctx = canvas.getContext('2d');
let cWidth = canvas.width;
let cHeight = canvas.height;

let honeyImg = new Image();
honeyImg.src = "honey.png"; // 30 * 30
honeyImg.xPos = undefined;
honeyImg.yPos = undefined;

let bees = [];
for (let i = 0; i < 15; i++) {
	bees[i] = new Image();
	bees[i].src = "bee.jpg"; // 50 * 50
	bees[i].xPos = cWidth * Math.random();
	bees[i].yPos = cHeight * Math.random();
}

function main() {

	// beeImg.xPos = cWidth * Math.random();
	// beeImg.yPos = cHeight * Math.random();
	animate();
}
window.onload = main;

function animate() {
	for (let i = 0; i < bees.length; i++) {
		eraseBee(bees[i]);
		ctx.drawImage(bees[i], bees[i].xPos, bees[i].yPos); // 2th and 3th par are x, y locations

		if (bees[i].xPos - honeyImg.xPos < -50) {
			bees[i].xPos += 5 * Math.random();
		} else if (bees[i].xPos - honeyImg.xPos > 20) {
			bees[i].xPos -= 5 * Math.random();
		} else {
			bees[i].xPos += 5 * (Math.random() - 0.5);
		}

		if (bees[i].yPos - honeyImg.yPos < -50) {
			bees[i].yPos += 5 * Math.random();
		} else if (bees[i].yPos - honeyImg.yPos > 20) {
			bees[i].yPos -= 5 * Math.random();
		} else {
			bees[i].yPos += 5 * (Math.random() - 0.5);
		}

		if (bees[i].xPos > cWidth - 50) {
			bees[i].xPos -= 5 * (Math.random() - 0.5);
		} else if (bees[i].xPos < cWidth + 20) {
			bees[i].xPos += 5 * (Math.random() - 0.5);
		} else if (bees[i].yPos > cHeight - 50) {
			bees[i].yPos -= 5 * (Math.random() - 0.5);
		} else if (bees[i].yPos < cHeight + 20) {
			bees[i].yPos += 5 * (Math.random() - 0.5);
		}
	}

	document.onmousemove = function(e) {
		erase();
		ctx.drawImage(honeyImg, e.x - 15, e.y - 15);
		honeyImg.xPos = e.x - 15;
		honeyImg.yPos = e.y - 15;
	}

	window.requestAnimationFrame(animate);
}

function erase() {
	ctx.fillStyle = "#FFFFFF"; // cover the screen with same color (like erase)
	ctx.fillRect(0, 0, cWidth, cHeight);
}

function eraseBee(x) {
	ctx.fillStyle = "#FFFFFF"
	ctx.fillRect(x.xPos - 5, x.yPos -5, 60, 60);
}