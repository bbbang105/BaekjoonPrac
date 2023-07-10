import sys
input = sys.stdin.readline
INF = sys.maxsize
# 입력
V,E = map(int,input().split())
if E < 2:
    print(-1)
    exit(0)
dp = [[INF]*V for _ in range(V)]
for i in range(V):
    dp[i][i] = 0
for i in range(E):
    u,v,w = map(int,input().split())
    dp[u-1][v-1] = w
# 플로이드-워셜 알고리즘
for k in range(V):
    for i in range(V):
        for j in range(V):
            if dp[i][j] > dp[i][k] + dp[k][j]:
                dp[i][j] = dp[i][k] + dp[k][j]
# 최단 사이클 찾기
result = sys.maxsize
for i in range(V):
    for j in range(V):
        if i != j:
            Sum = dp[i][j] + dp[j][i]
            result = min(result,Sum)
# 출력
if result < INF:
    print(result)
else:
    print(-1)