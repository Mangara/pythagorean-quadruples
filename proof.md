# Enumerating Pythagorean quadruples

A <i>Pythagorean triple</i> is a 3-tuple of integers $(a, b, c)$ such that $a^2 + b^2 = c^2$, for example $(3, 4, 5)$. There is a nice recursive algorithm to generate Pythagorean triples that is based on the fact that they form [a ternary tree](https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples). In this paper, we derive a similar algorithm to generate Pythagorean quadruples.

As you can probably guess, a <i>Pythagorean quadruple</i> is a 4-tuple of integers $(a, b, c, d)$ such that $a^2 + b^2 + c^2 = d^2$. As an example, $(2, 6, 9, 11)$ is a Pythagorean quadruple, since $2^2 + 6^2 + 9^2 = 4 + 36 + 81 = 121 = 11^2$. Where a Pythagorean triple represents a right-angled triangle with integer sides and diagonal, a Pythagorean quadruple can be thought of as arising from a three-dimensional box with integer side lengths and interior diagonal.

There are infinitely many Pythagorean quadruples, so what does it mean to enumerate them? It means we want to find an algorithm that is guaranteed to eventually reach any Pythagorean quadruple. If we can find the tree structure we're looking for, we can navigate the tree depth-first until some stopping criterion is met.

## Transformation

The first thing we need for our tree is an operation that transforms one Pythagorean quadruple into another. There are sveral choices that work, but we'll be using the following:

```math
S(a, b, c, d) = (2a + 2b, 2a - 2b, 3c + d, c + 3d)
```

This is again a Pythagorean quadruple, as

```math
\begin{align*} 
  (2a + 2b)^2 + (2a - 2b)^2 + (3c + d)^2 &= (c + 3d)^2\\
  (4a^2 + 8ab + 4b^2) + (4a^2 - 8ab + 4b^2) + (9c^2 + 6cd + d^2) &= (c^2 + 6cd + 9d^2)\\
  8a^2 + 8b^2 + 8c^2 &= 8 d^2
\end{align*}
```