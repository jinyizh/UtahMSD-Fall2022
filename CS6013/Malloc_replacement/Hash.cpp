//
// Created by Jinyi Zhou on 3/15/23.
//

#include "Hash.h"

/**
 * Constructor for the hash table
 */
Table::Table() {
    this->capacity = 2000;
    this->size = 0;
    this->table = (Node*) mmap(nullptr, capacity * sizeof(Node), PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_PRIVATE, 0, 0);
    if (this->table == MAP_FAILED) {
        perror("error: mmap failed\n");
        exit(1);
    }
}

/**
 * Add items to the hash table
 * @param ptr void pointer to the memory location
 * @param size_memory size of the memory needed
 * @return whether insertion was successful or not
 */
bool Table::insert(void *ptr, size_t size_memory) {
    if (this->size > (capacity / 2)) rehash();
    int hash_index = hash(ptr);
    while (this->table[hash_index].ptr != nullptr) {
        if (this->table[hash_index].ptr == ptr) return false;
        hash_index++;
        hash_index %= this->capacity;
    }
    this->size++;
    this->table[hash_index].ptr = ptr;
    this->table[hash_index].size = size_memory;
    return true;
}

/**
 * Remove an item from the hash table
 * @param ptr void pointer to the memory location
 * @return whether removal was successful or not
 */
bool Table::remove(void *ptr) {
    int hash_index = hash(ptr);
    int count = 0;
    while (this->table[hash_index].ptr != ptr) {
        hash_index++;
        count++;
        if (count > this->capacity) return false;
        hash_index %= this->capacity;
    }
    this->table[hash_index].ptr = nullptr;
    this->table[hash_index].size = 0;
    this->size--;
    return true;
}

/**
 * Search for an item in the hash table
 * @param ptr void pointer to the memory location
 * @return size of the memory
 */
size_t Table::search(void *ptr) {
    int hash_index = hash(ptr);
    int count = 0;
    while (this->table[hash_index].ptr != ptr) {
        hash_index++;
        count++;
        if (count > this->capacity) return 0;
        hash_index %= this->capacity;
    }
    return this->table[hash_index].size;
}

/**
 * Get the size of the hash table
 * @return size of the hash table
 */
int Table::get_size() {
    return this->size;
}

/**
 * Do hashing
 * @param ptr void pointer to the memory location
 * @return hash index as an integer
 */
int Table::hash(void *ptr) {
    int hash = 10;
    int c = (size_t) ptr;
    hash = 37 * hash + c;
    return abs(hash) % this->capacity;
}

/**
 * Rehash the table when the size is larger than half of the capacity
 */
void Table::rehash() {
    int old_capacity = this->capacity;
    Node* old_table = this->table;
    this->capacity = old_capacity * 2;
    Node* temp_table = (Node*) mmap(nullptr, this->capacity * sizeof(Node), PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_PRIVATE, 0, 0);
    if (temp_table == MAP_FAILED) {
        perror("error: mmap failed\n");
        exit(1);
    }
    this->table = temp_table;
    this->size = 0;
    for (int i = 0; i < old_capacity; i++)
        if (old_table[i].ptr != nullptr)
            insert(old_table[i].ptr, old_table[i].size);
}

/**
 * Destructor of the hash table
 */
Table::~Table() {
    this->table = nullptr;
    this->size = 0;
    this->capacity = 0;
}
