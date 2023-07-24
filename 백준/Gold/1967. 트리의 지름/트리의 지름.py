import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

# dfs 함수
def dfs(x,W):
    for i in graph[x]:
        node,weight = i
        if distance[node] == -1:
            distance[node] = W + weight
            dfs(node,W + weight) # 다음 노드, 현재까지 거리

N = int(input())
graph = [[] for _ in range(N+1)]

# 트리 구현
for _ in range(N-1):
    a,b,c = map(int,input().split())
    # 양방향 간선
    graph[a].append((b,c))
    graph[b].append((a,c))
    
# 1번 노드에서 가장 먼 곳을 찾음
distance = [-1] * (N+1) # 1번노드부터 해당 위치까지의 거리
distance[1] = 0
dfs(1,0)

# 위에서 찾은 노드에 대한 가장 먼 노드를 찾아줌
start = distance.index(max(distance))
distance = [-1] * (N+1)
distance[start] = 0
dfs(start,0)

print(max(distance))