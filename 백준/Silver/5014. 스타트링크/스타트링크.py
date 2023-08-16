import sys
from collections import deque
input = sys.stdin.readline

def bfs(x):
    Q = deque()
    Q.append(x)
    while Q:   
        x = Q.popleft()
        for i in range(2):
            nx = x+dx[i]
            if 1 <= nx <= N and visited[nx] == -1:
                visited[nx] = visited[x] + 1
                Q.append(nx)
    
N,start,goal,up,down = map(int,input().split())
visited = [-1]*(N+1)
dx = [up,-down]
visited[start] = 0
bfs(start)
if visited[goal] == -1:
    print('use the stairs')
else:
    print(visited[goal])