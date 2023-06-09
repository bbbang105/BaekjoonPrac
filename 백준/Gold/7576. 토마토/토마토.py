from collections import deque
import sys
input = sys.stdin.readline

# BFS
def BFS():
    global Q
    
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]

    while Q:
        x,y = Q.popleft()

        for i in range(4):
            mx = x+dx[i]
            my = y+dy[i]
            if 0 <= mx < M and 0 <= my < N:
                # 익지 않은 토마토이거나, 더 짧은 일수를 발견했을 때
                if graph[mx][my] == 0 or graph[mx][my] > graph[x][y] + 1:
                    graph[mx][my] = graph[x][y] + 1 # 직전 일수 +1
                    Q.append((mx,my))

# main
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(M)]
Q = deque()
for x in range(N):
    for y in range(M):
        if graph[y][x] == 1: # 익은 토마토의 위치 파악
            Q.append((y,x))
BFS() # BFS 실행
            
# 탐색 완료된 그래프 파악하기
maxNum = 0
for i in graph:
    if 0 in i: # 토마토가 모두 익지 못하는 상황
        print(-1)
        exit(0)
    else:      # 토마토가 모두 익은 상황
        maxNum = max(maxNum,max(i))
                    
if maxNum == 1: # 처음부터 모든 토마토가 익어 있는 상태
    print(0)
else:
    print(maxNum-1) # 1부터 시작했기 때문에, -1하여 출력