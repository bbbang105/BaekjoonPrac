from itertools import combinations
import sys
input = sys.stdin.readline
# 입력
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]
ck = []
home = []
# 치킨집과 집의 위치 찾기
for i in range(N):
    for j in range(N):
        if graph[i][j] == 1:
            home.append((i,j))
        elif graph[i][j] == 2:
            ck.append((i,j))
# M개의 치킨집 조합
result = sys.maxsize
for comb in combinations(ck,M): # 모든 조합을 탐색
    Sum_comb = 0                # 각 조합별, 도시 치킨 거리의 최솟값
    # 각 집별로 가장 가까운 치킨 거리를 찾아 합함
    for h in home:
        shortest_ck = sys.maxsize
        for c in comb:
            shortest_ck = min(shortest_ck, abs(h[0]-c[0])+abs(h[1]-c[1]))
        Sum_comb += shortest_ck
    # 이번 조합이 이전보다 더 작다면
    result = min(result,Sum_comb)
# 출력
print(result)