# Question 1

Every time a packet is received at the router it is time-stamped (TA). It is also times- stamped when it is about to leave link K and arrive at the next hop (TB). Note that this would be a difficult measurement to actually perform!

When 5 packets of the size 500 bytes are transmitted through the link K, (TB – TA) is measured to be 10 ms, 2.8 ms, 2.4 ms, 4 ms, and 5.5 ms. When 5 packets of the size 1000 bytes are transmitted through the link K, (TB – TA) is measured to be 11.0 ms, 10 ms, 2.8 ms, 3.0 ms, and 5.5 ms. Assume that processing delay at the router is negligible.

What is the average queuing delay experienced by the 1000 byte packets?

What are the reasonable estimates of transmission and propagation delays experienced by a packet of size 600 bytes sent through the link K?

# Answer
Arrange the TB - TA for each 1000-byte packet from small to large, they are:

2.8 ms, 3.0 ms, 5.5 ms, 10 ms, 11.0 ms

Assuming that the samllest time has no queuing delay, the queuing delay for each packet is

0 ms, 0.2 ms, 2.7 ms, 7.2 ms, 8.2 ms

Therefore, the average queueing delay experienced by the 1000 byte packets is 3.66 ms.

---

For 500-byte packets, TB - TA are: 

2.4 ms, 2.8 ms, 4 ms, 5.5 ms, 10 ms

The smallest time has only transmission delay and propagation delay, that is:

2.4 = 500 / (transmission_rate + propagation_delay) = 2.8 = 1000 / (transmission_rate + propagation_delay)

Solve this group of equations, we get:

- Transmission rate R = 1250 bytes/ms
- Propagation delay= 2 ms

Therefore, the transmission delay for a 600-byte packets is 600 / 1250 = 0.48 ms, and the propagation delay is 2 ms.