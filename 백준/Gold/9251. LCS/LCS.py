import sys
input = sys.stdin.readline

W1 = input().strip()
W2 = input().strip()

dp = [0]*1000 # LCS 길이 저장

for i in range(len(W1)):
    mx = 0
    for j in range(len(W2)):
        if mx < dp[j]: # 현재까지의 최대 길이로 갱신
            mx = dp[j]
        elif W1[i] == W2[j]:
            dp[j] = mx + 1 # LCS 길이 + 1
            
print(max(dp))