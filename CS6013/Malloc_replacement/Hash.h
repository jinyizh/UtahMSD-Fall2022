//
// Created by Jinyi Zhou on 3/15/23.
//

#ifndef MALLOC_REPLACEMENT_HASH_H
#define MALLOC_REPLACEMENT_HASH_H

#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <sys/mman.h>

class Node;

class Table {
    int hash(void* ptr);
    void rehash();
public:
    int capacity;
    int size;
    Node* table;
    Table();
    bool insert(void* ptr, size_t size_memory);
    bool remove(void* ptr);
    size_t search(void* ptr);
    int get_size();
    ~Table();
};

class Node {
public:
    void* ptr;
    size_t size;
    Node(void* ptr, size_t size) {
        this->ptr = ptr;
        this->size = size;
    }
};

#endif //MALLOC_REPLACEMENT_HASH_H
