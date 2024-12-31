/*
 * Copyright 2024 Sander Verdonschot <sander.verdonschot at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public abstract class PythagoreanQuadrupleEnumerator {
    public void enumeratePrimitiveQuadruples() {
        generate(2, 2, 1, 3);
    }
    
    private void generate(long qa, long qb, long qc, long qd) {
        // qa > 0 and even, qb even, qc odd, qd > 1 and odd, qc + qd = 0 (mod 4)
        
        long a = qa;
        long b = Math.abs(qb);
        long c = Math.abs(qc);
        long d = qd;
        
        // All are non-negative, c is odd and the quadruple is primitive
        
        if (shouldStop(a, b, c, d)) {
            return;
        }

        handlePrimitiveQuadruple(a, b, c, d);
        
        // Recurse
        
        // We always have (qc + d) % 4 == 0; the same is not true for (c + d)
        long x = (d + qc) / 2;
        generate(a + b, a - b, x + qc, x + d);
        
        // a <-> c
        generate(2 * c + 2 * b, 2 * c - 2 * b, 3 * a + d, a + 3 * d);
        generate(2 * c + 2 * b, 2 * c - 2 * b, 3 * -a + d, -a + 3 * d); // We always have a > 0
        
        // b <-> c
        if (a != b) {
            generate(2 * a + 2 * c, 2 * a - 2 * c, 3 * b + d, b + 3 * d);
            if (b > 0) {
                generate(2 * a + 2 * c, 2 * a - 2 * c, 3 * -b + d, -b + 3 * d);
            }
        }
    }

    public abstract boolean shouldStop(long a, long b, long c, long d);
    
    /**
     * Process the primitive Pythagorean quadruple (a, b, c, d).
     * We have that:
     * 1. a^2 + b^2 + c^2 = d^2
     * 2. GCD(a, b, c, d) = 1
     * 3. a and b are even, c and d are odd
     * 4. all four are non-negative, although b may be 0.
     * @param a
     * @param b
     * @param c
     * @param d 
     */
    public abstract void handlePrimitiveQuadruple(long a, long b, long c, long d);
}
