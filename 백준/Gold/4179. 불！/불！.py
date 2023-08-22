import sys
from collections import deque
input = sys.stdin.readline
dx,dy = [-1,1,0,0],[0,0,-1,1]

def bfs():
    while Q:
        x,y = Q.popleft()
        # 상근이 이동 or 불 이동 판단
        if visited[x][y] != 'FIRE':
            flag = visited[x][y]
        else:
            flag = 'FIRE'

        for i in range(4):
            nx,ny = x+dx[i],y+dy[i]
            # 이동 가능한 경우
            if 0 <= nx < R and 0 <= ny < C:
                if visited[nx][ny] == -1 and (graph[nx][ny] == '.' or graph[nx][ny] == 'J'):
                    # 불이 번지는 경우
                    if flag == 'FIRE':
                        visited[nx][ny] = 'FIRE'
                    # 상근이가 이동하는 경우
                    else:
                        visited[nx][ny] = flag + 1
                    Q.append((nx,ny))
            # 범위를 넘어 탈출한 경우
            else:
                if flag != 'FIRE':
                    return flag + 1
                
    return 'IMPOSSIBLE'

R,C = map(int,input().split())
Q = deque()
graph = [list(input().strip()) for _ in range(R)]
visited = [[-1]*C for _ in range(R)]
for i in range(R):
    for j in range(C):
        if graph[i][j] == 'J':
            visited[i][j] = 0
            start = (i,j)
        elif graph[i][j] == 'F':
            visited[i][j] = 'FIRE'
            Q.append((i,j))
Q.append(start)
print(bfs())