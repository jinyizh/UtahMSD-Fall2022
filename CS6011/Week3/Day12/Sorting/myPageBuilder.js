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


let myArray = [1, 9, 4];
let myArray1 = ['abc', 1, 9, 2.22, -2, 'abd', 0];
selectionSort(myArray);
selectionSort(myArray1);
console.log(myArray);
console.log(myArray1);