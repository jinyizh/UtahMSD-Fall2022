# Makefile to compile and link the program

msdscript: main.o cmdline.o
	g++ -o msdscript main.o cmdline.o

main.o: main.cpp
	g++ -c main.cpp
cmdline.o: cmdline.cpp cmdline.h
	g++ -c cmdline.cpp

.PHONY: clean
clean:
	rm -rf *.o msdscript
