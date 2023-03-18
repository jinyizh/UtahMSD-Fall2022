//
// Created by Jinyi Zhou on 3/15/23.
//

#include <unistd.h>
#include "Malloc.h"

Malloc::Malloc(): table(Table()) { // initializer list is useful!
    std::cout << "hash table initialized" << std::endl;
}

void *Malloc::allocate(size_t bytesToAllocate) {
    void* ptr = mmap(NULL, bytesToAllocate, PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_PRIVATE, 0, 0);
    if (ptr == MAP_FAILED) {
        perror("error: mmap failed\n");
        exit(1);
    }
    this->table.insert(ptr, bytesToAllocate % getpagesize());
    return ptr;
}

void Malloc::deallocate(void *ptr) {
    size_t size = this->table.search(ptr);
    int rc = munmap(ptr, size);
    if (rc != 0) perror("error: de-allocation failed\n");
    else this->table.remove(ptr);
}
