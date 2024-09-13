def knapsack_dy_prog(W, V, M, n, dispenser_start_idx):
    # Initialize matrix B with zeros (n+1 rows and M+1 columns)
    B = [[0 for _ in range(M + 1)] for _ in range(n + 1)]

    # Fill the matrix B using dynamic programming
    for i in range(1, n + 1):  # Iterate over items (candy groups)
        for j in range(1, M + 1):  # Iterate over knapsack capacity
            if W[i - 1] <= j:  # If the group fits in the knapsack
                B[i][j] = max(B[i - 1][j], V[i - 1] + B[i - 1][j - W[i - 1]])
            else:  # If the group doesn't fit, don't include it
                B[i][j] = B[i - 1][j]

    return B[n][M]

def max_pezz_score(dispensers, max_candies):
    # Step 1: Transform dispensers into cumulative sums
    transformed_disp = []
    for dispenser in dispensers:
        cumulative = []
        current_sum = 0
        for i, candy in enumerate(dispenser):
            current_sum += candy
            cumulative.append((current_sum, i + 1))  # (sum, number of candies)
        transformed_disp.append(cumulative)

    # Step 2: Flatten the transformed list and extract sums and weights
    V = []  # Values (sum of candy scores)
    W = []  # Weights (number of candies)
    dispenser_start_idx = []  # Track where each dispenser starts

    # Flatten the dispenser list and keep track of dispenser indices
    idx = 0
    for group in transformed_disp:
        dispenser_start_idx.append(idx)
        for value, weight in group:
            V.append(value)
            W.append(weight)
        idx += len(group)

    # Max candies we can take
    n = len(V)  # Number of candy groups

    # Step 3: Create DP table with an extra dimension for tracking dispensers
    dp = [0] * (max_candies + 1)  # Max score for each candy limit

    for start_idx in dispenser_start_idx:
        # Work with only the groups from the current dispenser
        current_dp = dp[:]  # Make a copy of the current DP table
        for i in range(start_idx, start_idx + len(transformed_disp[dispenser_start_idx.index(start_idx)])):
            score, weight = V[i], W[i]
            for j in range(max_candies, weight - 1, -1):
                current_dp[j] = max(current_dp[j], dp[j - weight] + score)
        dp = current_dp  # Update the dp with the result from this dispenser

    return dp[max_candies]

# Example usage
dispensers = [
    [100, 2, 1, 6],  # First dispenser
    [2, 8, 9, 4]   # Second dispenser
]

dispensers1 = [
    [3, 6, 8, 2, 2, 5, 6, 1, 2, 3, 1, 4],
    [4, 1, 1, 1, 1, 70, 4, 1, 5, 3, 2, 3],
    [5, 3, 2, 6, 6, 2, 3, 1, 6, 2, 3, 2],
    [1, 4, 3, 8, 4, 9, 2,1 ,6, 5, 4, 1],
]
dispensers2 = [
    [20, 30, 40, 15, 1, 14, 200, 2, 300, 2, 2, 2]
]
matriz = [[5, 51, 2, 3, 1], [1, 1, 72, 1, 1], [10, 60, 2, 3, 10]]
max_candies = 7
print(max_pezz_score(dispensers1, max_candies))  # Output: Maximum score achievable
