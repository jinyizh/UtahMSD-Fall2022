# Question 1

Every time a packet is received at the router it is time-stamped (TA). It is also times- stamped when it is about to leave link K and arrive at the next hop (TB). Note that this would be a difficult measurement to actually perform!

When 5 packets of the size 500 bytes are transmitted through the link K, (TB – TA) is measured to be 10 ms, 2.8 ms, 2.4 ms, 4 ms, and 5.5 ms. When 5 packets of the size 1000 bytes are transmitted through the link K, (TB – TA) is measured to be 11.0 ms, 10 ms, 2.8 ms, 3.0 ms, and 5.5 ms. Assume that processing delay at the router is negligible.

What is the average queuing delay experienced by the 1000 byte packets?

What are the reasonable estimates of transmission and propagation delays experienced by a packet of size 600 bytes sent through the link K?

# Answer
Refers to 1.4.2:

transmission rate (the rate (in bits/sec) at which bits are pushed out of the queue) R = 

the average rate at which packets arrive at the queue (packets/sec) a = 

Therefore, the traffic intensity I = La / R = 