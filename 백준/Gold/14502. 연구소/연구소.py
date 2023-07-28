import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

def bfs(x,y):
    Q = deque()
    Q.append((x,y))
    visited[x][y] = True

    while Q:
        x,y = Q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M and (nx,ny) not in wall_lst:
                # 바이러스 퍼뜨리기
                if graph[nx][ny] == 0 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    del_lst.append((nx,ny))
                    Q.append((nx,ny))
    
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]

# 초기 빈 칸, 바이러스 위치 파악
safe_lst,birus_lst = [],[]
for i in range(N):
    for j in range(M):
        if graph[i][j] == 0:
            safe_lst.append((i,j))
        elif graph[i][j] == 2:
            birus_lst.append((i,j))
            
dx = [-1,1,0,0]
dy = [0,0,-1,1]
# 조합을 이용해 브루트포스           
res = 0
for wall_lst in combinations(safe_lst,3):
    visited = [[False]*M for _ in range(N)]
    del_lst = []
    # 바이러스 위치만으로 bfs 실행
    for x,y in birus_lst:
        bfs(x,y)
    # 안전영역 = (초기 빈 칸 개수 - 바이러스에 감염된 개수 - 벽의 개수 3)
    res = max(res, len(safe_lst) - len(del_lst) - 3)
    
print(res)