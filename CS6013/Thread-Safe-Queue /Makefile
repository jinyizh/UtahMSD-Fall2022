# For testing concurrent queue
CXX = g++
CFLAGS = -std=c++17 -fsanitize=undefined -fno-sanitize-recover=undefined
SRCS = ConcurrentQueueTest.cpp
HDRS = ConcurrentQueue.h
OBJS = ConcurrentQueueTest.o

ConcurrentQueueTest: $(OBJS)
	$(CXX) $(CFLAGS) -o ConcurrentQueueTest $(OBJS)

ConcurrentQueueTest.o: $(SRCS) $(HDRS)
	$(CXX) $(CFLAGS) -c $(SRCS)

.PHONY: clean

clean:
	rm -rf *.o ConcurrentQueueTest