cmake_minimum_required(VERSION 3.24)
project(Malloc_replacement)

set(CMAKE_CXX_STANDARD 17)

add_executable(Malloc_replacement main.cpp Malloc.cpp Malloc.h Hash.cpp Hash.h)
