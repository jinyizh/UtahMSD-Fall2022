let heading = document.createElement("H1");
let titleText = document.createTextNode("10 * 10 Multiplication Table");
heading.appendChild(titleText);
document.body.appendChild(heading);

const tbl = document.createElement("table");
const tblBody = document.createElement("tbody");

  for (let i = 0; i < 10; i++) { // i is # of row
    // create row
    const row = document.createElement("tr");
    for (let j = 0; j < 10; j++) { // j is # of column
      const cell = document.createElement("td"); // create cells in row
      let num = (i + 1) * (j + 1);
      let rowString = `${i}`;
      let colString = `${j}`;
      let id = rowString + colString;
      const btn = document.createElement("button");
      const btnText = document.createTextNode(num);
      btn.appendChild(btnText);
      btn.id = id;
      let upID = '0' + `${j}`; // corresponding btn in top row
      let leftID = `${i}` + '0';  // corresponding btn in leftmost column

      // functions for mouse hovering
      btn.onmouseover = function() {
        if (btn.style.backgroundColor != "lightblue") {
            btn.style.backgroundColor = "lightgrey";
        }
      }
      btn.onmouseout = function() {
        if (btn.style.backgroundColor != "lightblue") {
            btn.style.backgroundColor = "";
        }
      }
      // functions for clicking
      btn.onclick = function() {
        for (let k = 0; k < 10; k++) {
            for (let l = 0; l < 10; l++) {
                let rowString1 = `${k}`;
                let colString1 = `${l}`;
                let id1 = rowString1 + colString1;
                if (document.getElementById(id1).style.backgroundColor = "lightblue") {
                    document.getElementById(id1).style.backgroundColor = "";
                }
            }
        }
        // above is used to remove highlighting
        btn.style.backgroundColor = "lightblue";
        document.getElementById(upID).style.backgroundColor = "lightblue";
        console.log(document.getElementById(upID).style.backgroundColor); 
        document.getElementById(leftID).style.backgroundColor = "lightblue";
      }
      cell.appendChild(btn);
      row.appendChild(cell);
    }
    // add row to table body
    tblBody.appendChild(row);
  }

  // add table body to table
  tbl.appendChild(tblBody);
  // add table into page body
  document.body.appendChild(tbl);
  // set border
  tbl.setAttribute("border", "5");

var i = 0;
function change() {
  var doc = document.body;
  var color = ["white", "blue", "brown", "green"];
  doc.style.backgroundColor = color[i];
  i = (i + 1) % color.length;
}
setInterval(change, 1000);

let btn = document.getElementById("btn");
btn.onclick = toggle;

function toggle() {
  document.body.style.backgroundColor = "red";
}
window.onload(change);