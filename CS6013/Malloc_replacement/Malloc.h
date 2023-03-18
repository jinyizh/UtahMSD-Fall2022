//
// Created by Jinyi Zhou on 3/15/23.
//

#ifndef MALLOC_REPLACEMENT_MALLOC_H
#define MALLOC_REPLACEMENT_MALLOC_H

#include "Hash.h"

class Malloc {
    Table table;
public:
    Malloc();
    void* allocate(size_t bytesToAllocate);
    void deallocate(void* ptr);
};

#endif //MALLOC_REPLACEMENT_MALLOC_H
