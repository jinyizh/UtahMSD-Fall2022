// this function is used to find the index of the smallest value in a array
function findMinLocation(array, iteration) {
    // let smallest = array[iteration];
    let smallestIndex = iteration;
    for (let i= iteration + 1; i < array.length; i++) {
        if (array[i] < array[smallestIndex]) smallestIndex = i;
    }
    return smallestIndex;
}

function selectionSort(array) {
    for (let i = 0; i < array.length; i++) {
        // Swap smallest value with the current location
        let sIndex = findMinLocation(array, i);
        let temp = array[i];
        array[i] = array[sIndex];
        array[sIndex] = temp;
    }
}

// array of ints, floating point numbers, strings, and a mix of these
let myArray = [1, 9, 4];
let myArray1 = [-0.2, -9, 3.2, 1.8];
let myArray2 = ['abs', 'aadvark', 'apple', 'zebra', 'monkey']
let myArray3 = ['Abs', 'aadvark', 'apple', 'zebra', 'monkey']
let myArray4 = ['abc', 1, 9, 2.22, -2, 'abd', 0];
selectionSort(myArray);
selectionSort(myArray1);
selectionSort(myArray2);
selectionSort(myArray3);
selectionSort(myArray4);
console.log(myArray);
console.log(myArray1);
console.log(myArray2);
console.log(myArray3);
selectionSort(myArray4);

function compareTo(a, b) {
    if (a < b) {
        return 1;
    } else if (a > b) {
        return 0;
    } else {
        return -1;
    }
}

function newSelectionSort(array, compareTo) {
    function newFindMinLocation(array, iteration) {
        let smallestIndex = iteration;
        for (let i= iteration + 1; i < array.length; i++) {
            if (compareTo(array[i], array[smallestIndex]) == 1) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }
    for (let i = 0; i < array.length; i++) {
        let sIndex = newFindMinLocation(array, i);
        let temp = array[i];
        array[i] = array[sIndex];
        array[sIndex] = temp;
    }
}

let person = {}; // person object
bee.first = "John";
bee.last = "Smith";

let person1 = {};
person1.first = "John";
person1.last = "Doe";

let person2 = {};
person2.first = "Jane";
person2.last = "Doe";

let newMyArray = [1, 9, 4];
let newMyArray1 = ['abc', 1, 9, 2.22, -2, 'abd', 0];
newSelectionSort(myArray);
newSelectionSort(myArray1);
console.log(myArray);
console.log(myArray1);