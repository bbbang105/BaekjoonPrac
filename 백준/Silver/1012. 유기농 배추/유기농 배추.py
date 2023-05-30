import sys
sys.setrecursionlimit(10000) # 최대 재귀 깊이 늘려주기
input = sys.stdin.readline

# DFS
def DFS(x,y):
    # 상하좌우
    dx = [1,-1,0,0] 
    dy = [0,0,-1,1]
    
    for i in range(4):
        nx = x + dx[i]   # 움직일 x좌표
        ny = y + dy[i]   # 움직일 y좌표
        if (0 <= nx < M) and (0 <= ny < N):
            if graph[ny][nx] == 1:
                graph[ny][nx] = -1 # 방문 여부는 -1로 표시
                DFS(nx,ny)
                
for _ in range(int(input())):
    
    # 그래프 생성
    M,N,K = map(int,input().split())
    graph = [[0]*M for _ in range(N)]
    earthworms = 0
    
    # 배추 심어주기 (1표시)
    for i in range(K):
        x,y = map(int,input().split())
        graph[y][x] = 1 
    
    # DFS로 필요한 지렁이 숫자(= 배추 그룹 수) 세기   
    for x in range(M):
        for y in range(N):
            if graph[y][x] == 1:
                DFS(x,y)
                earthworms += 1
    
    print(earthworms)