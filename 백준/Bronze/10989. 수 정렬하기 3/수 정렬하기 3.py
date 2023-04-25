import sys
input = sys.stdin.readline

dp = [0]*10001                  # 10,000개의 숫자가 존재하므로
for _ in range(int(input())):
    dp[int(input())] += 1       # 각 숫자가 나온만큼 count
    
for i in range(10001):
    if dp[i] != 0:
       for j in range(dp[i]):   # count한만큼 print
           print(i)             