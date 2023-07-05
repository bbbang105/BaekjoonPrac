from collections import deque
import sys
input = sys.stdin.readline
INF = sys.maxsize

N,M = map(int,input().split())
graph = [input().rstrip() for _ in range(M)]
dist = [[-1]*N for _ in range(M)]

def BFS(x,y):
    dist[x][y] = 0
    Q = deque()
    Q.append((x,y))
    
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]

    while Q:
        x,y = Q.popleft()
        
        for i in range(4):
            mx = x + dx[i]
            my = y + dy[i]
            if 0 <= mx < M and 0 <= my < N:
                # 첫 방문일 경우
                if dist[mx][my] == -1:
                    # 벽인 경우
                    if graph[mx][my] == '1':
                        dist[mx][my] = dist[x][y] + 1
                        Q.append((mx,my)) # 앞에 삽입
                    # 벽이 아닌 경우
                    else:
                        dist[mx][my] = dist[x][y]
                        Q.appendleft((mx,my)) # 앞에 삽입

    return dist[M-1][N-1]        

print(BFS(0,0))