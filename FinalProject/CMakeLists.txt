cmake_minimum_required(VERSION 3.15)

project(FinalProject)

add_executable(FinalProject src/main.cpp)

find_package(SFML 2.5 COMPONENTS graphic REQUIRED)

target_link_libraries(FinalProject sfml-graphics)

target_compilre_options(FinalProject PUBLIC -std=c++17)
