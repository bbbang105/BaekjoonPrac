import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
graph = [input() for _ in range(N)]
change = [[-1]*N for _ in range(N)]

def BFS(x,y):
    change[x][y] = 0
    Q = deque()
    Q.append((x,y))
    
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]
    
    while Q:
        x,y = Q.popleft()
        
        for i in range(4):
            mx = x + dx[i]
            my = y + dy[i]
            if 0 <= mx < N and 0 <= my < N:
                # 첫 방문인 경우
                if change[mx][my] == -1:
                    # 검은 방을 만난 경우
                    if graph[mx][my] == '0':
                        change[mx][my] = change[x][y] + 1
                        Q.append((mx,my))
                    # 흰 방을 만난 경우
                    else:
                        change[mx][my] = change[x][y]
                        Q.appendleft((mx,my))
                        
    return change[N-1][N-1]

print(BFS(0,0))