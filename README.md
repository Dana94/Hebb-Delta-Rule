I learned about the Hebb Rule and Delta Rule in an Into Artificial Intelligence class. I chose to create a program that accepts a user's inputs and output the result.

User gives: learning rate (e), maximum difference in the calculated output compared to the actual output (d), origin vector, number of iterations (to avoid the program from going on forever), choice of whether to use Hebb or Delta Rule, 3 input vectors

The Hebb Rule's formula: Δ𝑊_𝑖𝑗 = e * 𝑈_𝑖  ∗ 𝑈𝑗 
  
The Delta Rule's formula: Δ𝑊_𝑖𝑗 = e * 𝑈_𝑖 * (𝑡_𝑗 - 𝑦_𝑗)

Where 𝑡_𝑗 = target U4 and 𝑦_𝑗 = actual U4
  
