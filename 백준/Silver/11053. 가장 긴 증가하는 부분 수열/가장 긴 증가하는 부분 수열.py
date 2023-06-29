import sys
input = sys.stdin.readline

N = int(input())
dp = [1 for _ in range(N)]
A = list(map(int,input().split()))

for i in range(N):
    for j in range(i):
        if A[i] > A[j]:
            dp[i] = max(dp[i],dp[j] + 1)
            
print(max(dp))