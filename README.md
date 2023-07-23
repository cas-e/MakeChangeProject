# MakeChangeProject

## Overview

The classic change making problem. When a user gives an item cost and an amount received from a customer, the program determines how much of each denomination of currency to hand back to the customer. It is defined on U.S. currency.

Example interaction:

~~~
Please enter price of the item:
3.96
Please enter amount received from customer: 
20.00
Customer needs the following: 
Tens:     1
Fives:    1
Dollars:  1
Pennies:  4
~~~

## Technologies Utilized
- Java
- Eclipse
- Git
- Github

## Problem Background

The change making problem surprisingly interesting. The solution used in this code is known as a "greedy" algorithm, and is intuitively what anyone would do as a cashier. It has probably been known for as long as people have used currency. It's fast, simple, and it works for *most* types of currency, where it always finds the minimum amount of coins needed to hand back to the customer. It successfully finds the minimum number of coins on the U.S. system, for example. But what if the currency had coins like {4, 3, 1} and we wanted to hand back and amount of 6? The greedy algorithm fails to find an optimal minimal solution:

~~~
// take the maximum amount of the highest currency at each step
amountDue = 6
handBack  = new array[3]
coinSet   = [4 3 1]

for (index, coin) in coinSet {
	handBack[index] = div(amountDue, coin)
	      amountDue = mod(amountDue, coin)
}

// handBack == [1 0 2]
~~~

The above code hands out one 4-piece coin and two 1-piece coins. The optimal solution is `[0 2 0]` or two 2-piece coins. 

The general solution over all all possible coin sets was, as far as I can find, first given by Chang and Gill \[1\] in 1970. In that paper they supply an algorithm where any coin set (which the paper calls the weight set W), may be supplied as a parameter. Or in their words, W may be "supplied externally (for example, from punched cards)". Impressively, their program correctly finds the minimum set of coins to hand back for any W. Also impressively, THEY WROTE THESE PROGRAMS ON PUNCHED CARDS! 

 Their solution, and others like it, ended up being generalized into methods we now call "dynamic programming with backtracking". Near the end of the paper, they attempt an analysis of when a coin set is or is not amenable to the much simpler greedy algorithm (what they call "the standard algorithm".) This turns out to be a hard problem and led to much future research, with papers on the topic being published even now in 2023, 53 years after the initial paper. \[2\]

## Lessons Learned

So what did I learn?

Nothing is as simple as it seems. I knew my program could fail to find good answers on some currency sets, but I couldn't explain why or when that could happen. This would be important to know, I reasoned, if updates needed to be made later to add a new type of coin into the CoinSet.

 I tried for some time to figure it out and got stuck. Lots of googling and stack-overflowing and trying to read old research papers later, and it turns out that explaining it is still an open problem. In the research, "explaining it" means finding a simple general characterization for when CoinSets will or won't be amenable to greediness. There may even be no solution, but no one knows for sure. Although recent progress in small cases as in \[2\] may suggest a solution exists.

But for strictly practical day to day purposes, we can consider the problem solved. Several algorithms exist to check whether a *particular* CoinSet will be amenable to greediness when handing back change less than some N. In other words, if a client comes to us and wants to add a new type of coin to the CoinSet, we can algorithmically determine whether we would need to upgrade the program from the simple greedy algorithm to the more complicated backtracking one. And that seems good enough in practice to me. 


## References

\[1\] Algorithmic Solution of the Change-Making Problem
S. K. CHANG AND A. GILL
Journal of the ACM, Volume 17, Issue 101, January 1970, pp 113–122
https://doi.org/10.1145/321556.321567

\[2\] Characterization of canonical systems with six types of coins for the change-making problem
Yuma Suzuki, Ryuhei Miyashiro
Theoretical Computer ScienceVolume 955, 2023, 113822, ISSN 0304-3975,
https://doi.org/10.1016/j.tcs.2023.113822.
