from collections import deque
import sys
input = sys.stdin.readline

N,M,V = map(int,input().split())
graph = [[] for i in range(N+1)]
for _ in range(M):                  # 그래프 생성
    a,b = map(int,input().split())
    graph[a] += [b]
    graph[b] += [a]
 
def DFS(graph,start,visited = []):  # DFS 함수
    visited.append(start)
    for i in sorted(graph[start]):  # 번호가 작은 것부터 탐색
        if i not in visited:
            DFS(graph,i,visited)
    return visited
    
def BFS(graph,start):               # BFS 함수
    Q = deque([start])
    visited = [start]
    while Q:
        n = Q.popleft()
        for i in sorted(graph[n]):  # 번호가 작은 것부터 탐색
            if i not in visited:
                Q.append(i)
                visited.append(i)
    return visited

print(*DFS(graph,V))
print(*BFS(graph,V))