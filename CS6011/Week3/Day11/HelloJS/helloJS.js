// document.writeln("Hello World!"); // Display hello world in HTML page using document.writeln()
console.log("Hello World!"); // Display hello world in HTML page using console.log()

/* 
The differences between using document.writeln() and using console.log() are:
1. If using document.writelin(), the content is shown on the web page itself,
   while console.log() only shows the content in the console
   (on Safari, you have to open Develop -> Show Web Inspector -> console).
2. Also, document.writelin() will overwrite the current web content, while console.log() will not.
*/

myArray = ['hello', true, 2, 3.14, {'name': 'John', 'GPA': 3.9}]; // array containing a string, a boolean, an int, a floating-point number, and an object.
console.log(myArray);
/* Open the file with Safari, and the web inspector -> console shows:
Array (5)
0 "hello"
1 true
2 2
3 3.14
4 Object
    GPA: 3.9
    name: "John"
It also shows the Object prototype and array prototype.
If the properties of the array are changed, then the console only shows the array itself, and it can't be opened.
*/

function f(a, b) {
    return a + b;
}

let myFunction = function(a, b){
    return a + b;
}

// I prefer the first method, becasue for the second method, the function is treated as a variable, which is not the same in C++ and Java.
// test functions using ints, floating point numbers, strings, and a mix of these:

console.log(f(1, 2)); // int & int, result is 3
console.log(f(2.2, 3.3)); // float & float, result is 5.5
console.log(f('hello', 'world')); // string & string, result is helloworld
console.log(f(1, 'hello')); // int & string, result is 1hello
console.log(f(1, 2.2)); // int & float, result is 3.2
console.log(f(2.2, 'world')); // float & string, result is 2.2world