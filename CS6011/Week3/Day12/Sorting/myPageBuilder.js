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

let myArray = [1, 9, 4];
let myArray1 = ['abc', 1, 9, 2.22, -2, 'abd', 0];
selectionSort(myArray);
selectionSort(myArray1);
console.log(myArray);
console.log(myArray1);

function compareTo(a, b) {
    return a < b;
}

function newSelectionSort(array, compareTo) {
    function newFindMinLocation(array, iteration) {
        let smallestIndex = iteration;
        for (let i= iteration + 1; i < array.length; i++) {
            if (compareTo(array[i], array[smallestIndex])) {
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

let newMyArray = [1, 9, 4];
let newMyArray1 = ['abc', 1, 9, 2.22, -2, 'abd', 0];
newSelectionSort(myArray);
newSelectionSort(myArray1);
console.log(myArray);
console.log(myArray1);