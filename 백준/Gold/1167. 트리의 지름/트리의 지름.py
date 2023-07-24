import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**7)

def dfs(X,W):
    for node,weight in graph[X]:
        if distance[node] == -1:
            distance[node] = W + weight
            dfs(node,W + weight)

N = int(input())
graph = [[] for _ in range(N+1)]
# 트리 구현
for _ in range(N):
    info = list(map(int,input().split()))
    node1 = info[0] # 현재 노드 번호
    info = info[1:-1]
    for j in range(0,len(info)-1,2):
        node2 = info[j] # 연결된 노드 번호
        weight = info[j+1] # 거리
        graph[node1].append((node2,weight))
        
# 1번 노드에서 가장 거리가 먼 노드 찾기
distance = [-1] * (N+1)
distance[1] = 0
dfs(1,0)

# 위에서 찾은 노드에서 가장 거리가 먼 노드 찾기
start = distance.index(max(distance))
distance = [-1] * (N+1)
distance[start] = 0
dfs(start,0)

print(max(distance))