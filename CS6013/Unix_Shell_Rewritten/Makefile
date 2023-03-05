CXX = g++

CFLAGS = -std=c++17

INCS = shelpers.hpp

OBJS = main.o shelpers.o

shell: $(OBJS)
	$(CXX) $(CFLAGS) -lreadline -o shell $(OBJS)

main.o : main.cpp $(INCS)
	$(CXX) $(CFLAGS) -c main.cpp

shelpers.o : shelpers.cpp $(INCS)
	$(CXX) $(CFLAGS) -c shelpers.cpp

.PHONY: clean

clean :
	rm -f $(OBJS) shell
