import sys
input = sys.stdin.readline
INF = sys.maxsize
# 입력
V,limit,E = map(int,input().split())    # 노드의 개수, 수색 범위, 간선의 개수
items = list(map(int,input().split()))  # 각 구역에 있는 아이템의 수
# 초기 그래프 설정
dp = [[INF]*V for _ in range(V)]
for i in range(V):
    dp[i][i] = 0 # 본인은 0
for _ in range(E):
    u,v,weight = map(int,input().split())
    # 양방향이므로
    if weight < dp[u-1][v-1]:
        dp[u-1][v-1] = weight
    if weight < dp[v-1][u-1]:
        dp[v-1][u-1] = weight
# 플로이드-워셜 알고리즘    
for k in range(V):
    for i in range(V):
        for j in range(V):
            if dp[i][j] > dp[i][k] + dp[k][j]:
                dp[i][j] = dp[i][k] + dp[k][j]
# 출력
result = 0                
for i in range(V):
    Sum = 0
    for j in range(V):
        # 수색 범위를 초과하는 경우
        if dp[i][j] > limit:
            continue
        Sum += items[j]
    result = max(result,Sum)
print(result)