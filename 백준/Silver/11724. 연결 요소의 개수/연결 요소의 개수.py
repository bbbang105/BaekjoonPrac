from collections import deque
import sys
input = sys.stdin.readline

N,M = map(int,input().split())

graph = [[] for i in range(N+1)]        # 그래프 초기화
for _ in range(M):                      # 그래프 생성
    a,b = map(int,input().split())
    graph[a] += [b]                     # 양방향 연결
    graph[b] += [a]
visited = [0]*(N+1)                     # 방문기록 초기화

def bfs(node):                          # bfs
    Q = deque([node])
    while Q:
        v = Q.popleft()
        for i in graph[v]:
            if visited[i] == 0:
                Q.append(i)
                visited[i] = 1

cnt = 0                                 # 연결요소의 개수
for i in range(1,N+1):
    if visited[i] == 0:
        bfs(i)
        cnt += 1
print(cnt)