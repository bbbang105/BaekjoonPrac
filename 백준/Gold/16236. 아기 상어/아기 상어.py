import sys
from collections import deque
input = sys.stdin.readline

def bfs(move,x,y):
    Q = deque()
    Q.append((move,x,y))
    visited = [[False]*N for _ in range(N)]
    visited[x][y] = True
    
    while Q:
        move,x,y = Q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < N:
                # 본인보다 작은 물고기를 먹은 경우
                if size > graph[nx][ny] > 0 and not visited[nx][ny]:
                    # 먹이 후보에 추가
                    visited[nx][ny] = True
                    cand.append((move+1,nx,ny))
                # 지나갈 수 있는 경우        
                elif size >= graph[nx][ny] and not visited[nx][ny]:
                    # 방문 처리 후 이동
                    visited[nx][ny] = True
                    Q.append((move+1,nx,ny))

N = int(input())
graph = [list(map(int,input().split())) for _ in range(N)]

dx = [-1,0,0,1]
dy = [0,1,-1,0]
            
# 상어 위치 찾기
for i in range(N):
    for j in range(N):
        if graph[i][j] == 9:
            graph[i][j] = 0
            start_x,start_y = i,j # 초기 위치
            
# 아기 상어 이동
size = 2
feed = 0
res = 0            
while True:
    cand = []
    bfs(0,start_x,start_y)
    # 더이상 먹을 물고기가 없는 경우
    if not cand:
        print(res)
        break
    # 후보 중 가장 위이고 왼쪽인 물고기 선택
    else:
        cand.sort(key = lambda x : (x[0],x[1],x[2]))
        move,x,y = cand[0]
        res += move
        graph[x][y] = 0
        start_x,start_y = x,y
    # 먹은 횟수 + 1
    feed += 1
    # 몸집이 커지는 경우
    if feed == size:
        size += 1
        feed = 0