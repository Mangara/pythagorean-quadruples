# Enumerating Pythagorean quadruples

A <i>Pythagorean triple</i> is a 3-tuple of integers $(a, b, c)$ such that $a^2 + b^2 = c^2$, for example $(3, 4, 5)$. There is a nice recursive algorithm to generate Pythagorean triples that is based on the fact that they form [a ternary tree](https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples). In this article, we derive a similar algorithm to generate Pythagorean quadruples.

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
  (2a + 2b)^2 + (2a - 2b)^2 + (3c + d)^2 &= (c + 3d)^2 \\
  (4a^2 + 8ab + 4b^2) + (4a^2 - 8ab + 4b^2) + (9c^2 + 6cd + d^2) &= (c^2 + 6cd + 9d^2) \\
  8a^2 + 8b^2 + 8c^2 &= 8d^2.
\end{align*}
```

## Structure

Next, let's investigate the structure of Pythagorean quadruples a little more. First, it's easy to see that if $(a, b, c, d)$ is a Pythagorean quadruple then so is $(ka, kb, kc, kd)$ for any positive integer $k$. We call a Pythagorean quadruple <i>primitive</i> if the greatest common divisor (GCD) of all four elements is 1. If we can enumerate primtive quadruples, it's easy to generate the rest from those by multipliying each element, so from now we'll focus just on primitive quadruples.

Second, if $(a, b, c, d)$ is a primtive Pythagorean quadruple, then $d$ is odd and so is exactly one of $a$, $b$, and $c$. This can be shown by looking at the squares modulo 4. If $x$ is even, $x^2 = 0 (\pmod 4)$, and if $x$ is odd, $x^2 = 1 (\pmod 4)$. Therefore the only way for $d$ to be even is if $a$, $b$, and $c$ are even, which is impossible if the quadruple is primitive. So $d$ must be odd and we have $a^2 + b^2 + c^2 = 1 (\pmod 4)$. That is only possible if exactly one of them is odd (contributing $\'1 (\pmod 4)\'$) and the other two are even (contributing $\'0 (\pmod 4)\'$).

Since the order of $a$, $b$, and $c$ does not matter, we will assume from here on that in a primitive Pythagorean quadruple $(a, b, c, d)$, $a$ and $b$ are even and $c$ and $d$ are odd.

By looking at the squares mod 8, we can further ascertain that $a$ and $b$ must be equivalent mod 4. In other words, either both $a$ and $b$ are divisible by 4, or neither are.



