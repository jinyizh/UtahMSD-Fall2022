//
// Created by Jinyi Zhou on 4/11/23.
//

#include <cassert>
#include <thread>
#include "ConcurrentQueue.h"

bool testQueue(int num_producers, int num_consumers, int num_ints);

int main(int argc, char* argv[]) { // for this test, use make first and then type command
    int num_producers = std::stoi(argv[1]);
    int num_consumers = std::stoi(argv[2]);
    int num_ints = std::stoi(argv[3]);
    assert(testQueue(num_producers, num_consumers, num_ints));
    std::cout << "Test passed" << std::endl;
}

bool testQueue(int num_producers, int num_consumers, int num_ints) {
    // a. Create an std::vector of std::threads.
    std::vector<std::thread> producers;
    std::vector<std::thread> consumers;

    // b. reserve space in this vector for all producer and consumer threads
    producers.reserve(num_consumers);
    consumers.reserve(num_consumers);

    // c. Create a ConcurrentQueue object statically.
    ConcurrentQueue<int> concurrentQueue; // statically!
    int* a = new int;
//    std::mutex mutex;

    // d. Create num_producer producer threads that enqueue num_ints ints into the ConcurrentQueue.
    for (int i = 0; i < num_producers; i++) {
        producers.emplace_back([&]() {
            for (int j = 0; j < num_ints; ++j) {
                concurrentQueue.enqueue(j);
            }
        });
    }

    // e. Create num_consumer consumer threads that dequeue num_ints ints from the ConcurrentQueue.
    for (int i = 0; i < num_consumers; i++) {
        consumers.emplace_back([&]() {
            for (int j = 0; j < num_ints; ++j) {
                concurrentQueue.dequeue(a);
            }
        });
    }

    // f. Wait for all threads to join.
    for (int i = 0; i < num_producers; i++) producers[i].join();
    for (int i = 0; i < num_consumers; i++) consumers[i].join();
    delete a;

    // g. Returns 1 if the number of elements in the queue matches (num_producers - num_consumers)*num_ints, 0 otherwis
    return concurrentQueue.size() == (num_producers - num_consumers) * num_ints;
}
