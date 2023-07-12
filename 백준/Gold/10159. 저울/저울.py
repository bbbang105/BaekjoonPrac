import sys
input = sys.stdin.readline
INF = sys.maxsize
# 입력
V = int(input())
E = int(input())
dp = [[0]*V for _ in range(V)]
for _ in range(E):
    small,big = map(int,input().split())
    dp[small-1][big-1] = 1
# 플로이드-워셜 알고리즘
for k in range(V):
    for i in range(V):
        for j in range(V):
            if dp[i][k] and dp[k][j]:
                dp[i][j] = 1
# 비교 결과를 알 수 없는 물건의 개수 출력
for i in range(V): # 1번 노드부터 탐색
    small = 0       
    for j in range(V):
        small += dp[j][i] # 자신보다 작은 물건의 개수
    big = sum(dp[i])      # 자신보다 큰 물건의 개수
    # (자신보다 작은 물건의 개수 + 큰 물건의 개수)는 비교를 할 수 있는 물건의 개수
    result = (V-1) - (small + sum(dp[i])) # 본인은 제외해야므로, V-1에서 빼줌
    print(result)            