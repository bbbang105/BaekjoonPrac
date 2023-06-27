import sys
input = sys.stdin.readline

N = int(input())
dp = [list(map(int,input().split())) for _ in range(N)]

for i in range(1,N):
    for j in range(i+1):
        left = j-1
        right = j
        num = 0
        if left < 0:
            num = dp[i-1][right]
        elif right >= len(dp[i-1]):
            num = dp[i-1][left]
        else:
            num = max(dp[i-1][right],dp[i-1][left])
        dp[i][j] += num

print(max(dp[-1]))