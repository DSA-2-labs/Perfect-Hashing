# Perfect Hashing

## Contents:
- [Introduction](#Introduction)
- [Universal Hashing](#Universal-Hashing)
  - [Thereom 1](#Thereom-1)
  - [Constructing a Universal Hash Family: The Matrix Method](#Constructing-a-Universal-Hash-Family:-The-Matrix-Method)
- [Perfect Hashing](#Perfect-Hashing)
  - [O(N<sup>2</sup>) - Space Solution](#O(N<sup>2</sup>)-Space-Solution)
  - [O(N) - Space Solution](#O(N)-Space-Solution)
- [Application](#Application)
  - [English Dictionary](#English-Dictionary)
  - [Command Line Interface](#Command-Line-Interface)
- [Comparison](#Comparison)
  - [Number of rebuilds](#Number-of-rebuilds)
  - [Space](#Space)

---

## 1. Introduction
Implementation of a perfect hashing data structure. We say a hash function is perfect for S if all lookups involve O(1) work. In section 2, background about
universal hashing is provided. Sections 3 and 4 describe two methods for constructing perfect hash functions for a given set S. It's required to design, analyze and implement a perfect
hash table as described in sections 3 and 4.

---

## Universal Hashing
A probability distribution H over hash functions from U to {1, ..., M} is universal if for all x=y in U, we have<br>
Pr[h(x) = h(y)] ≤ 1/M


### 2.1 Thereom 1
If H is universal, then for any set S ⊂ U, for any x ∈ U (that we might want to insert or lookup), for a random h taken from H, the expected number of collisions between x and other elements in S is at most N/M.


### 2.2 Constructing a Universal Hash Family: The Matrix Method
Let’s say keys are u-bits long. Say the table size M is power of 2, so an index is b-bits long with M = 2<sup>b</sup>. What we’ll do is pick h to be a random b-by-u 0/1 matrix, and define h(x)=hx, where we do addition mod 2. For instance:

![image](https://github.com/mohamedhassan279/Perfect-Hashing/assets/96317608/0fdb189d-a2dd-467e-99d9-2a38fce6224f)

We can show that for x = y, Pr[h(x) = h(y)] = 1/M = 1/2<sup>b</sup>

---

## 3. Perfect Hashing:

### 3.1 O(N<sup>2</sup>) - Space Solution
Say we are willing to have a table whose size is quadratic in the size N of our dictionary S. Then, here is an easy method. Let H be universal and M = N<sup>2</sup>. Pick a random h from H and try it out, hashing everything in S. So, we just try it, and if we got any collisions, we just try a new h. On average, we will only need to do this twice.


### 3.2 O(N)-Space Solution
The main idea for this method is to use universal hash functions in a 2-level scheme. The method is as follows. We will first hash into a table of size N using universal hashing. This will produce some collisions. However, we will then rehash each bin using Method 1, squaring the size of the bin to get zero collisions. So, the way to think of this scheme is that we have a first-level hash function h and first-level table A, and then N second-level hash functions h1, ..., hN and N second-level tables A1, ..., AN. To look up an element x, we first compute i = h(x) and then find the element in Ai[hi(x)].

---

## 4 Application:

### 4.1 English Dictionary
As an application based on the perfect hashing implementation, it's required to implement a simple English dictionary supporting the following functionalities:
1. Initialize (constructor): Takes the name of the type of the backend perfect hashing as an input and creates a new empty dictionary based on it.
2. Insert: Takes a single string key and tries to insert it.
3. Delete: Takes a single string key and tries to delete it.
4. Search: Takes a single string key, searches for it, and returns true if it exists and false otherwise.
5. Batch insert: Takes a path to a text file containing multiple words each in a separate line. And tries to insert all that words into the dictionary.
6. Batch delete: Takes a path to a text file containing multiple words each in a separate line. And tries to delete all that words from the dictionary.


### 4.2 Command Line Interface
Implement a command line interface that will enable us to deal with the dictionary
and apply all its implemented operations. This interface must take the type of hash table as an initial input then create a dictionary based on it and allow the user to apply subsequent operations on it from the following list:
1. Insert a string and prints a confirmation message or an error one if the the string already exists in the dictionary.
2. Delete a string and prints a confirmation message or an error one if the the string doesn’t exist in the dictionary.

---

## 5. Comparison

### 5.1 Number of rebuilds:

| size | Hash-O(N) | Hash-O(N<sup>2</sup>) |
| ---- | --------- | --------------------- |
| 10 | 1 | 0 |
| 100 | 41 | 0 |
| 1,000 | 468 | 0 |
| 10,000 | 3292 | 0 |
| 20,000 | 6388 | 0 |


### 5.2 Space:

| size   | Hash-O(N) | Hash-O(N<sup>2</sup>) |
| ------ | --------- | --------------------- |
| 10     | 14        | 128                   |
| 100    | 185       | 16,384                |
| 1,000  | 1,965     | 1,048,576             |
| 10,000 | 16,288    | 134,217,728           |
| 20,000 | 33,349    | 536,870,912           |
| 30,000 | 57,782    | 1,073,741,824         |

![image](https://github.com/mohamedhassan279/Perfect-Hashing/assets/96317608/ddc17f28-289f-4303-bded-ae7deabefb14)

---
