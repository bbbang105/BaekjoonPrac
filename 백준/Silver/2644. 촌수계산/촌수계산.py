import sys
from collections import deque
input = sys.stdin.readline

def bfs():
    while Q:
        p,c = Q.popleft()
        for i in range(1,N+1):
            if graph[c][i] and not visited[c][i]:
                visited[c][i] = True
                graph[c][i] = graph[p][c] + 1
                if i == n2:
                    return graph[c][i]
                Q.append((c,i))
            
N = int(input())
n1,n2 = map(int,input().split())
if n1 > n2:
    n1,n2 = n2,n1
V = int(input())
graph = [[0]*(N+1) for _ in range(N+1)]
for _ in range(V):
    p,c = map(int,input().split())
    graph[p][c] = 1
    graph[c][p] = 1

visited = [[False]*(N+1) for _ in range(N+1)]
Q = deque()
for i in range(1,N+1):
    if graph[n1][i]:
        Q.append((n1,i))
        if i == n2:
            print(1)
            exit(0)
            
res = bfs()
if res:
    print(res)
else:
    print(-1)