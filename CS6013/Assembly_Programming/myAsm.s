extern puts
extern myPuts
global sayHello                         ; this says that "sayHello" is a symbol that the linker needs to know about

section .text                           ; this says that we're about to write code (as opposed to data)

sayHello:                               ; and here we go...
    push    rbp                         ; push rbp onto the stack, making it 8 byte aligned
    mov     rdi, helloString
    call    puts
    pop     rbp                         ; pop it off the stack so that the caller's return address is now on top of the stack
    ret                                 ; just return

myPuts:
    mov     rdx, rsi
    mov     rsi, rdi
    mov     rdi, 1
    mov     rax, 1
    syscall
    ret

section .rodata                         ; this is the read only data (hello is a constant)

helloString: db "hello", 0              ; hellostring is the name of our symbol
                                        ; db is the directive to put data in our object file
                                        ; the 0 is the null terminator that puts is expecting.
                                        ; nasm does NOT null terminate our string automatically
