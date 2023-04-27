# Macros
CXX = /opt/homebrew/opt/llvm/bin/clang++
CFLAGS = -std=c++20
CLEAN = rm -rf
CXXSOURCE = $(wildcard *.cpp)
OBJECTS := main.o
COMPILING = -c
OPENMP_COMPILING = -fopenmp
OPENMP_LINKING = -lomp
OPENMP_LIB_PATH = -L/opt/homebrew/opt/libomp/lib
LINKING = -o

$(info CXX path: $(shell which $(CXX)))

parallelReduction: $(OBJECTS)
	$(CXX) $(CFLAGS) $(OBJECTS) $(LINKING) parallel_reduction $(OPENMP_LIB_PATH) $(OPENMP_LINKING)

main.o: $(HEADERS) $(CXXSOURCE) main.cpp
	$(CXX) $(CFLAGS) $(COMPILING) main.cpp

.PHONY: clean
clean:
	$(CLEAN) *.o parallel_reduction

.PHONY: test
test: parallel_reduction
	./parallel_reduction