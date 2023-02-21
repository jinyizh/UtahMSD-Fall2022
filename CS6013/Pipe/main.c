#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char *argv[]) {
    int fildes[2]; // pipe
    char *input = argv[1];
    if (pipe(fildes) == -1) { // 0 on success
        perror("error: opening pipe failed");
        exit(1);
    }
    pid_t rc = fork();
    if (rc > 0) { // parent
        printf("parent, pid: %d\n", (int) getpid());
        close(fildes[0]); // close the end we aren't concerned with (reading)
        write(fildes[1], input, strlen(input) + 1);
        if (write(fildes[1], input, strlen(input) + 1) == -1) {
            perror("error: writing to pipe failed");
            exit(1);
        }
        printf("parent wrote: %s\n", input);
        int wait = waitpid(rc, NULL, 0);
        printf("waiting for: %d\n", wait);
        close(fildes[1]); // close writing
    }
    else if (rc < 0) {
        perror("error: fork failed\n");
        exit(1);
    }
    else if (rc == 0) { // child
        close(fildes[1]); // close writing
        printf("child, pid: %d\n", (int) getpid());
        read(fildes[0], input, strlen(input) + 1);
        if (read(fildes[0], input, strlen(input) + 1) == -1) {
            perror("error: reading file failed");
            exit(1);
        }
        printf("child read: %s\n", input);
        close(fildes[0]);
    }
    return 0;
}
