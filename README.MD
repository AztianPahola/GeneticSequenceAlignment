# Problem Statement
Given 2 genetic sequences, find the optimal alignment.

# Solution Summary
Using the principle of Dynamic Programming, I will work backwards. Starting at the end of the gene sequences,
the course of action between inserting a gap in the first gene sequence, inserting a gap in the second gene sequence,
or letting the two genes align will be chosen. This will create a optimal path matrix, as well as create the optimal sequences.