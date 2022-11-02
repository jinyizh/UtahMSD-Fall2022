'use strict';

function selectionSort(inputArray, comparator) {
    for (let i = 0; i < inputArray.length; i++) {
        let smallLocation = findMinLocation(inputArray, i, comparator);
        let temp = inputArray[i];
        inputArray[i] = inputArray[smallLocation];
        inputArray[smallLocation] = temp;
    }
}

function findMinLocation(inputArray, startingIndex, comparator) {
    let minValue = inputArray[startingIndex];
    let location = startingIndex;
    for (let i = startingIndex; i < inputArray.length; i++) {
        if (comparator(inputArray[i], minValue) == -1) {
            minValue = inputArray[i];
            location = i;
        }
    }
    return location;
}

function compareTo(a, b) {
    if (a > b) {
        return 1;
    }
    else if (a == b) {
        return 0;
    }
    return -1;
}

// Q: Try changing the comparison in compareTo() from "<" to ">".
//    What happens when you sort now?
// A: selectionSort() will sort the array same as before.

let testArray = [2, 10, 3, 1];
selectionSort(testArray, compareTo);
console.log(testArray);

let testArray2 = [2, -3, -10, 3];
selectionSort(testArray2, compareTo);
console.log(testArray2);

let testFloatArray = [3.14, 0.0, 2.5, -4.3];
selectionSort(testFloatArray, compareTo);
console.log(testFloatArray);

let testStringArray = ["Hello ", "ni", "hao", "you"];
selectionSort(testStringArray, compareTo);
console.log(testStringArray);

let testMixedArray = ["Hi, ", 2.5, -1, "xs"];
selectionSort(testMixedArray, compareTo);
console.log(testMixedArray);

let people = [
    { first: "Donald", last: "Trump" },
    { first: "Andrew", last: "Yang" },
    { first: "Joe", last: "Biden" },
    { first: "Kris", last: "Wu" },
    { first: "Sam", last: "Uncle" }
]

let people1 = [
    { first: "Donald", last: "Trump" },
    { first: "Andrew", last: "Yang" },
    { first: "Joe", last: "Biden" },
    { first: "Kris", last: "Yang" },
    { first: "Sam", last: "Uncle" }
]

function sortPeopleLast(array) {

    for (let i = 0; i < array.length; i++) {
        let minLocation = i;

        for (let j = i + 1; j < array.length; j++) {
            if ((array[j].last < array[minLocation].last) ||
                ((array[j].last == array[minLocation].last) && (array[j].first < array[minLocation].first)))
                minLocation = j; 
        }
        if (minLocation != i) {
            let temp = array[i]; 
            array[i] = array[minLocation];
            array[minLocation] = temp;      
        }
    }
    return array;
}

function sortPeopleByFirst(array) {
    for (let i = 0; i < array.length; i++) {
        let minLocation = i;

        for (let j = i + 1; j < array.length; j++) {
            if ((array[j].first < array[minLocation].first) ||
                ((array[j].first == array[minLocation].first) && (array[j].last < array[minLocation].last))) {
                minLocation = j; 
            }
        }
        if (minLocation != i) {
            let tmp = array[i]; 
            array[i] = array[minLocation];
            array[minLocation] = tmp;      
        }
    }
    return array;
}

sortPeopleLast(people);
sortPeopleByFirst(people1);
console.log(people);
console.log(people1);
