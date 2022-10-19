function findSmallestIndex(array, iteration) {
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
        let sIndex = findSmallestIndex(array, i);
        let temp = array[i];
        array[i] = array[sIndex];
        array[sIndex] = temp;
    }
}

// can also make a nested loop instead of having two functions

function main() {
    let myArray = [1, 9, 4];
    selectionSort(myArray);
}