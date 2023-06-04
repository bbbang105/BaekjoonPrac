from collections import deque
import sys,math
input = sys.stdin.readline

def BFS(startX,startY):
    Q = deque()
    Q.append((startX,startY))
    visit_num = 2
    while Q:
        x,y = Q.popleft()

        dx = [1,-1,0,0]
        dy = [0,0,-1,1]

        move_x,move_y = 0,0
        for i in range(4):
            move_x = x+dx[i]
            move_y = y+dy[i]
            if 0 <= move_x < N and 0 <= move_y < M:
                if graph[move_x][move_y] == 1:
                    Q.append((move_x,move_y))
                    graph[move_x][move_y] = graph[x][y] + 1
    
    return graph[N-1][M-1]

N,M = map(int,input().split())
graph = [list(map(int,input().rstrip())) for _ in range(N)] # 그래프 초기화
print(BFS(0,0))