import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y,c):
    Q = deque()
    Q.append((x,y,c))
    visited[x][y] = group_num # 그룹 번호로 변경
    cnt,near,near_lst = 1,1,[]
    while Q:
        x,y,c = Q.popleft()
        for i in range(4):
            nx,ny = x+dx[i],y+dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                if c:
                    if graph[nx][ny] == '0' and visited[nx][ny] == -1:
                        visited[nx][ny] = group_num
                        Q.append((nx,ny,1))
                        cnt += 1
                else:
                    if graph[nx][ny] == '0':
                        near_lst.append(visited[nx][ny])
    # 인접한 개수로 그래프 변경                    
    if not c:
        for g in set(near_lst):
            if g in group.keys():
                near += group[g]
        graph[x][y] = str(near%10)
    else:
        return cnt           

N,M = map(int,input().split())
graph = [list(input().strip()) for _ in range(N)]
dx,dy = [-1,1,0,0],[0,0,-1,1]
group = dict()
group_num = 2
wall_lst = []
visited = [[-1]*M for _ in range(N)]
for i in range(N):
    for j in range(M):
        # 빈칸인 경우 주변 빈칸의 개수를 세어줌
        if graph[i][j] == '0' and visited[i][j] == -1:
            cnt = bfs(i,j,1) # 주변 빈칸의 숫자
            group[group_num] = cnt # 그룹에 추가    
            group_num += 1 # 그룹 숫자 + 1
        elif graph[i][j] == '1':
            wall_lst.append((i,j))
            
for x,y in wall_lst:
    bfs(x,y,0)
for row in graph:
    print(*row,sep = '',end = '\n')