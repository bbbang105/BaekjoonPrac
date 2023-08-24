import sys
input = sys.stdin.readline
w1 = input().strip()
w2 = input().strip()
w3 = input().strip()
dp = [[[0]*(len(w3)+1) for _ in range(len(w2)+1)] for _ in range(len(w1)+1)]

for i in range(1,len(w1)+1):
    for j in range(1,len(w2)+1):
        for k in range(1,len(w3)+1):
            # 세 문자열이 동일할 경우
            if (w1[i-1] == w2[j-1] == w3[k-1]):
                dp[i][j][k] = dp[i-1][j-1][k-1] + 1
            # 동일하지 않은 경우
            else:
                # 이전 값들 중 가장 큰 값을 넣어줌
                dp[i][j][k] = max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1])     
                           
print(dp[len(w1)][len(w2)][len(w3)])