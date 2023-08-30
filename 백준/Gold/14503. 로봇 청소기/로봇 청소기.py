import sys
input = sys.stdin.readline

N,M = map(int,input().split())
x,y,D = map(int,input().split())
# 초기 방향
if D == 1:
    D = 3
elif D == 3:
    D = 1
graph = [list(map(int,input().split())) for _ in range(N)]
dx,dy = [-1,0,1,0],[0,-1,0,1] # 북서남동
res = 0
while True:
    # 현재 칸이 청소되지 않은 경우
    if graph[x][y] == 0:
        res += 1
        graph[x][y] = -1 # 청소완료 처리
    # 주변 칸 탐색
    back = True
    for i in range(1,5): # 발견을 해도 우선 90도 돌려야하기 때문에, 1부터 시작
        i = (D+i)%4
        nx,ny = x+dx[i],y+dy[i]
        # 청소 안 한 빈칸 발견
        if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == 0:
            back = False
            x,y = nx,ny
            D = i # 방향 변경
            break
    # 후진해야하는 경우
    if back:
        nx,ny = x-dx[D],y-dy[D]
        if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == 1:
            print(res)
            break
        else:
            x,y = nx,ny