//
// Created by Jinyi Zhou on 4/6/23.
//

#include <cassert>
#include "SerialQueue.hpp"

int main() { // for this test, simply press the run button
    // ----- test serial queue static alloc/dealloc ----- //

    SerialQueue<int> serialQueue; // static
    int* i = new int;

    serialQueue.enqueue(1);
    assert(serialQueue.size() == 1);
    serialQueue.enqueue(2);
    assert(serialQueue.size() == 2);

    assert(serialQueue.dequeue(i));
    assert(*i == 1);
    assert(serialQueue.dequeue(i));
    assert(*i == 2);
    assert(serialQueue.dequeue(i) == false);

    delete i;

    // ----- test serial queue dynamic alloc/dealloc ----- //

    auto* serialQueue1 = new SerialQueue<int*>; // dynamic
    int** j = new int*;

    serialQueue1->enqueue(new int(1));
    assert(serialQueue1->size() == 1);
    serialQueue1->enqueue(new int(2));
    assert(serialQueue1->size() == 2);

    assert(serialQueue1->dequeue(j));
    assert(**j == 1);
    assert(serialQueue1->dequeue(j));
    assert(**j == 2);
    assert(serialQueue.dequeue(*j) == false);

    delete j;
    delete serialQueue1;
}