cmake_minimum_required(VERSION 3.24)
project(Own_UNIX_Shell)

set(CMAKE_CXX_STANDARD 17)

add_executable(Own_UNIX_Shell main.cpp shelpers.hpp shelpers.cpp)
