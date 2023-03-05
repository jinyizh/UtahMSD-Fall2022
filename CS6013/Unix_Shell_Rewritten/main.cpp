#include <cstdio>
#include <iostream>
#include <sstream>
#include <vector>
#include "shelpers.hpp"

int main() {
    std::string input;
    while(getline(std::cin, input)) {
        if (input.empty()) continue; // handles empty input, won't work if converted to Command
        std::vector<std::string> tokens = tokenize(input);
        std::vector<Command> commands = getCommands(tokens);
        std::vector<pid_t> pids; // vector of pids
        for (int i = 0; i < commands.size(); i++) {
            if (commands[i].exec == "exit" ||
                commands[i].exec == "bye" ||
                commands[i].exec == "quit") {
                exit(0);
            }
            if (commands[i].exec == "cd") {
                if (commands[i].argv.size() == 2) chdir(getenv("HOME"));
                else if (commands[i].argv.size() > 2) chdir(commands[i].argv[1]);
                else perror("error: chdir failed\n");
            }
            pid_t rc = fork(); // fork
            if (rc < 0) {
                perror("error: fork failed\n");
                exit(1);
            }
            else if (rc == 0) { // child
                dup2(commands[i].fdStdin, STDIN_FILENO); // STDIN_FILENO is 0
                dup2(commands[i].fdStdout, STDOUT_FILENO); // STDOUT_FILENO is 1
                if (dup2(commands[i].fdStdin, 0) == -1) {
                    perror("error: file descriptor stdin copy failed\n");
                    exit(1);
                }
                dup2(commands[i].fdStdout, 1);
                if (dup2(commands[i].fdStdout, 1) == -1) {
                    perror("error: file descriptor stdout copy failed\n");
                    exit(1);
                }
                int ret = execvp(commands[i].exec.c_str(), // loaded into caller's address and overwrites
                                 const_cast<char *const *>(commands[i].argv.data())); // starts execution
                if (ret == -1) {
                    perror("error: execvp failed\n");
                    exit(1);
                }
            }
            else { // parent
                if (commands[i].fdStdin != STDIN_FILENO)
                    close(commands[i].fdStdin);
                if (commands[i].fdStdout != STDOUT_FILENO)
                    close(commands[i].fdStdout);
                pids.push_back(rc);
                if (!commands[i].background) for (pid_t p: pids) waitpid(p, nullptr, 0);
                else for (pid_t p: pids) std::cout << p << std::endl;
            }
        }
        input.clear();
    }
    return 0;
}