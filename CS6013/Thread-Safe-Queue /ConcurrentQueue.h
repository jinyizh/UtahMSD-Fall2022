#pragma once
#include <iostream>

template <typename T>

class ConcurrentQueue {
private:
    struct Node {
        T data;
        Node* next;
    };

    Node* head;
    Node* tail;
    int size_;

    std::mutex enqueue_lock;
    std::mutex dequeue_lock;

public:
    ConcurrentQueue(): head(new Node{T{}, nullptr}), size_(0) {
        tail = head;
    }

    // adds a new node at the tail of the linked list.
    void enqueue(const T& x) {
        std::lock_guard<std::mutex> lock(enqueue_lock);
        this->tail->next = new Node{x, nullptr};
        this->tail = this->tail->next;
        this->size_++;
    }

    // removes a node from the head of the linked list, and returns the data in the variable ret.
    // If the queue is empty, dequeue returns false. If an element was dequeued successfully, dequeue returns true.
    bool dequeue(T* ret) {
        std::lock_guard<std::mutex> lock(dequeue_lock);
        if (this->head->next == nullptr) return false;
        Node* tmp = this->head;
        *ret = tmp->next->data;
        this->head = tmp->next;
        this->size_--;
        delete tmp;
        return true;
    }

    ~ConcurrentQueue() {
        while (head) {
            Node* temp = head->next;
            delete head;
            head = temp;
        }
    }

    int size() const {
        return size_;
    }
};
