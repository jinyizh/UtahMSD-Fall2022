# Makefile - build
# rule "clothes" is used to link .o files into executable
# rule "main.o" depends on main.cpp. It is used to generate .o file from main.cpp
# rule "Caps.o" depends on Caps.cpp and Caps.h. It is used to generate Caps.o
# rule "Clothing.o" depends on Clothing.cpp and Clothing.h. It generates Clothing.o
# rule "Pants" depends on Pants.cpp and Pants.h. It generates Pants.o
# rule "clean" deletes the executale file clothes, and all the files end with .o

CXX = g++
CFLAGS = -o
CXXSOURCE = main.cpp Caps.cpp Clothing.cpp Pants.cpp
HEADERS = Caps.h Clothing.h Pants.h

build: clothes
clothes: main.o Caps.o Clothing.o Pants.o
	g++ -o clothes main.o Caps.o Clothing.o Pants.o
main.o: main.cpp
	g++ -c main.cpp
Caps.o: Caps.cpp Caps.h
	g++ -c Caps.cpp
Clothing.o: Clothing.cpp Clothing.h
	g++ -c Clothing.cpp
Pants.o: Pants.cpp Pants.h
	g++ -c Pants.cpp

# rule for printing the macros
printM: $(CXXSOURCE) $(HEADERS)
	echo $(CXX)
	echo $(CFLAGS)
	echo $(CXXSOURCE)
	echo $(HEADERS)

# generalized rule that creates object files
obj: $(CXXSOURCE)
	$(CXX) -c $(CXXSOURCE)
# rule that creates an executable of the program - Part 2
exe: clothes_part2
clothes_part2: $(CXXSOURCE)
	$(CXX) $(CXXSOURCE) $(CFLAGS) clothes_part2

.PHONY: clean
clean:
	rm -rf *.o clothes clothes_part2
