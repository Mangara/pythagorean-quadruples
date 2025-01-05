# Enumerating Pythagorean quadruples

A <i>Pythagorean triple</i> is a 3-tuple of integers $(a, b, c)$ such that $a^2 + b^2 = c^2$, for example $(3, 4, 5)$. There is a nice recursive algorithm to generate Pythagorean triples that is based on the fact that they form [a ternary tree](https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples). In this article, we derive a similar algorithm to generate Pythagorean quadruples.

As you can probably guess, a <i>Pythagorean quadruple</i> is a 4-tuple of integers $(a, b, c, d)$ such that $a^2 + b^2 + c^2 = d^2$. As an example, $(2, 6, 9, 11)$ is a Pythagorean quadruple, since $2^2 + 6^2 + 9^2 = 4 + 36 + 81 = 121 = 11^2$. Where a Pythagorean triple represents a right-angled triangle with integer sides and diagonal, a Pythagorean quadruple can be thought of as arising from a three-dimensional box with integer side lengths and interior diagonal.

There are infinitely many Pythagorean quadruples, so what does it mean to enumerate them? It means we want to find an algorithm that is guaranteed to eventually reach any Pythagorean quadruple. If we can find the tree structure we're looking for, we can navigate the tree depth-first or beadth-first until some stopping criterion is met.

## Structure

Let's start by looking at the structure of Pythagorean quadruples. First, it's easy to see that if $(a, b, c, d)$ is a Pythagorean quadruple then so is $(ka, kb, kc, kd)$ for any positive integer $k$. We call a Pythagorean quadruple <i>primitive</i> if the greatest common divisor (GCD) of all four elements is 1. If we can enumerate primtive quadruples, it's easy to generate the rest from those by multipliying each element, so from now we'll focus just on primitive quadruples.

Second, if $(a, b, c, d)$ is a primtive Pythagorean quadruple, then $d$ is odd and so is exactly one of $a$, $b$, and $c$. This can be shown by looking at the squares modulo 4. If an integer $x$ is even, $x^2 = 0 \pmod 4$, and if $x$ is odd, $x^2 = 1 \pmod 4$. Therefore the only way for $d$ to be even is if $a$, $b$, and $c$ are even, which is impossible if the quadruple is primitive. So $d$ must be odd and we have $a^2 + b^2 + c^2 = 1 \pmod 4$. That is only possible if exactly one of them is odd (contributing $`1 \pmod 4`$) and the other two are even (contributing $`0 \pmod 4`$).

Since the order of $a$, $b$, and $c$ does not matter, we will assume from here on that in a primitive Pythagorean quadruple $(a, b, c, d)$, $a$ and $b$ are even, and $c$ and $d$ are odd.

By looking at the squares mod 8, we can further ascertain that $a$ and $b$ must be equivalent mod 4. In other words, either both $a$ and $b$ are divisible by 4, or neither are.

## Equivalence classes

While Pythagorean quadruples are typically given with positive integers, it will turn out to be useful for us to allow some of the integers to be zero or negative. This means that there are many ways to represent "the same" Pythagorean quadruple, like $(4, 8, 1, 9)$ and $(-8, 4, -1, 9)$. We'd like our algorithm to only generate one of these, not both.

To be a little more precise, we define the <i>class</i> $[(a, b, c, d)]$ of a Pythagorean quadruple $(a, b, c, d)$ to be all those Pythagorean quadruples that can be derived from it by: (1) switching the sign of any of the four terms, (2) exchanging any of the first three terms, and (3) multiplying or dividing each term by the same number. So both $(-8, 4, -1, 9)$ and $(32, 4, -16, 36)$ are members of the class $[(4, 8, 1, 9)]$.

Our enumeration algorithm will enumerate one <i>representative</i> Pythagorean quadruple from each class. If the others are needed, they can be generated easily from these representatives by applying the three operations.

## Transformation

The last thing we need for our tree is an operation that transforms one Pythagorean quadruple into another. There are several choices that work, but we'll be using the following:

```math
S(a, b, c, d) = (2a + 2b, 2a - 2b, 3c + d, c + 3d)
```

This is again a Pythagorean quadruple, as

```math
\begin{align*} 
  (2a + 2b)^2 + (2a - 2b)^2 + (3c + d)^2 &= (c + 3d)^2 \\
  (4a^2 + 8ab + 4b^2) + (4a^2 - 8ab + 4b^2) + (9c^2 + 6cd + d^2) &= (c^2 + 6cd + 9d^2) \\
  8a^2 + 8b^2 + 8c^2 &= 8d^2.
\end{align*}
```

## Tree

We now have all the pieces we need to define our tree. Let $T$ be the graph whose vertices are the equivalence classes of Pythagorean quadruples, and with an edge between two classes $C_1$ and $C_2$ if and only if there exists a Pythagorean quadruple $A \in C_1$ such that $S(A) \in C_2$.

It is clear that $T$ is a graph that contains all (classes of) Pythagorean quadruples, but it is not immediately obvious that it is a tree. A tree is a connected, acyclic, undirected graph.