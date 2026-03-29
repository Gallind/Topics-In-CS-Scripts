# Topics in Computer Science - Algorithmic Portfolio
## Reichman University Honors Program

This repository is a comprehensive portfolio of foundational algorithms in computer science, number theory, and cryptography, implemented as part of the Honors Program at Reichman University. The project focuses on "from-scratch" implementations of complex mathematical operations, emphasizing arbitrary-precision arithmetic and optimized cryptographic protocols.

## Core Features
* **Arbitrary-Precision Arithmetic**: Custom implementations for operations like division and modular multiplication to handle integers of any bit-length using Java's `BigInteger`.
* **Efficiency-Focused Design**: Utilization of advanced algorithms such as Karatsuba multiplication to reduce time complexity for large-scale computations.
* **Cryptographic Rigor**: Full implementations of standard protocols, including Diffie-Hellman over prime-order subgroups and RSA with Chinese Remainder Theorem (CRT) optimizations.

## Implementation Highlights

### 1. Large Integer Operations (HW1, HW2, HW3)
* **Karatsuba Multiplication**: Implemented a divide-and-conquer approach for fast integer multiplication, verified against standard libraries with 512-bit inputs.
* **Zeckendorf Representation**: A unique representation of integers as sums of non-consecutive Fibonacci numbers.
* **Arbitrary-Length Division**: A recursive division algorithm providing both quotient and remainder for arbitrary-length integers.
* **Extended Euclidean Algorithm**: Implementation to find the greatest common divisor and modular multiplicative inverses.

### 2. Primality Testing & Sampling (HW6)
* **Miller-Rabin Test**: A probabilistic primality test used for identifying large primes with high accuracy.
* **Prime Sampling**: Logic to efficiently generate random 512-bit primes, requiring an average of approximately 187 attempts per prime.

### 3. Cryptographic Protocols (HW7, HW8)
* **Diffie-Hellman Key Agreement**: Secure key exchange over a multiplicative group $\mathbb{Z}_p^*$ using primes of the form $p = 2q + 1$ and generators found within the subgroup of quadratic residues.
* **Textbook RSA**: A complete public-key system including key generation, encryption, and an optimized decryption algorithm using the Chinese Remainder Theorem.

## Project Structure
* **`CommonFunctions/`**: Utility package containing `BigRandom` for secure integer generation and tuple classes for multi-value returns.
* **`hw1/` – `hw8/`**: Individual modules for each assignment, containing source code and execution logs (e.g., `Q4.txt`) demonstrating correctness with 512-bit parameters.
* **`assignments/`**: PDF documentation detailing the theoretical requirements and problem statements for each implementation.

## Technical Requirements
* **Java Development Kit (JDK)**: Version 21.
* **Dependencies**: The project relies exclusively on the standard `java.math.BigInteger` library.

## Execution
To run a specific simulation, such as RSA encryption:
1.  Navigate to the root directory.
2.  Compile the desired package: `javac hw8/*.java`
3.  Run the main class: `java hw8.RSA`
