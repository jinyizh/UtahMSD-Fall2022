cmake_minimum_required(VERSION 3.24)
project(Thread_Safe_Queue_)

set(CMAKE_CXX_STANDARD 17)

add_executable(Thread_Safe_Queue_ SerialQueue.hpp SerialQueueTests.cpp ConcurrentQueue.h ConcurrentQueue.h ConcurrentQueueTest.cpp ConcurrentQueueTest.cpp)
