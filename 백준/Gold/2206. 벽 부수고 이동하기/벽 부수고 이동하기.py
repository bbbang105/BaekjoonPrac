import sys
from collections import deque
input = sys.stdin.readline

N,M = map(int,input().split())
graph = [list(map(int,input().rstrip())) for _ in range(N)]

# 3차원 배열 사용: visited[x][y][0]은 벽 부수기 불가능, visited[x][y][1]은 가능인 상태의 횟수를 저장
visited = [[[0]*2 for _ in range(M)] for _ in range(N)]
visited[0][0][1] = 1

# 상하좌우
dx = [1,-1,0,0]
dy = [0,0,-1,1]

def BFS(a,b,c):
    Q = deque()
    Q.append((a,b,c))
    
    while Q:
        x,y,chance = Q.popleft()
        
        # 도착한 경우
        if x == N-1 and y == M-1:
            return visited[x][y][chance]
        for i in range(4):
            mx = x + dx[i]
            my = y + dy[i]
            if 0 <= mx < N and 0 <= my < M:
                # 이동할 곳이 벽이지만, 부술 기회가 남아있어 파괴할 수 있는 경우
                if graph[mx][my] == 1 and chance:
                    # 기회를 사용했기 때문에, visited[mx][my][0]에 저장함
                    visited[mx][my][0] = visited[x][y][1] + 1
                    Q.append((mx,my,0))
                # 이동할 곳이 벽이 아닌 경우
                elif graph[mx][my] == 0 and not visited[mx][my][chance]:
                    visited[mx][my][chance] = visited[x][y][chance] + 1
                    Q.append((mx,my,chance))
                    
    return -1 # 도착할 수 없는 경우

# 벽을 부술 기회를 1개 제공한 상태로 시작
print(BFS(0,0,1))