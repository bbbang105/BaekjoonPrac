from collections import deque
import sys
input = sys.stdin.readline

# BFS
def BFS(x,y,lst):
    visited[x][y] = True
    Q.append((x,y))
    color = lst[x][y] # 집단의 색
    
    # 상하좌우
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]
    
    while Q:
        x,y = Q.popleft()
        for i in range(4):
            mx = x+dx[i] # 이동할 좌표
            my = y+dy[i]
            if 0 <= mx < N and 0 <= my < N:
                if not visited[mx][my] and lst[mx][my] == color:
                    visited[mx][my] = True
                    Q.append((mx,my))
                    
    return
                  
N = int(input())
normal = [input().rstrip() for _ in range(N)]   # 적록색약 X
abnormal = [i.replace('R','G') for i in normal] # 적록색약 O
twolst = [normal,abnormal]

for lst in twolst:
    visited = [[False for _ in range(N)] for _ in range(N)]
    cnt = 0
    Q = deque()
    for x in range(N):
        for y in range(N):
            if not visited[x][y]:
                BFS(x,y,lst)
                cnt += 1
    print(cnt,end = ' ')