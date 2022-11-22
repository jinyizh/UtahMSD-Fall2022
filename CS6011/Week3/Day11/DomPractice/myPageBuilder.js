let div1 = document.createElement("div");
document.body.appendChild(div1);

//create a paragraph
var para1 = document.createElement("p");
var text1 = document.createTextNode("This is the first paragraph.");
para1.appendChild(text1);
div1.appendChild(para1);

//create a button to change para1 color
function toGreen() {
    para1.style.color = "green";
}
function toRed() {
    para1.style.color = "red";
}
function toPink() {
    para1.style.color = "pink";
}
