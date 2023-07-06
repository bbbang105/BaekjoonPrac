import sys
from collections import deque
input = sys.stdin.readline

# BFS
def BFS(x,y):
    # 바깥쪽에서 들어오는 경우도 고려하기 위해, 외곽을 추가
    doors = [[-1]*(W+2) for _ in range(H+2)] 
    doors[x][y] = 0 
    Q = deque()
    Q.append((x,y))

    dx = [-1,1,0,0]
    dy = [0,0,-1,1]
    
    while Q:
        x,y = Q.popleft()
        
        for i in range(4):
            mx = x + dx[i]
            my = y + dy[i]
            if 0 <= mx < H+2 and 0 <= my < W+2:
                if doors[mx][my] == -1:
                    # 문을 열지 않는 경우
                    if graph[mx][my] == '.' or graph[mx][my] == '$':
                        Q.appendleft((mx,my)) # 덱 앞에 삽입
                        doors[mx][my] = doors[x][y]
                    # 문을 여는 경우    
                    elif graph[mx][my] == '#':
                        Q.append((mx,my))
                        doors[mx][my] = doors[x][y] + 1      
    return doors       

# Input    
T = int(input())
for _ in range(T):
    H,W = map(int,input().split())
    graph = ['.'*(W+2)] # 맨 윗줄 추가
    for _ in range(H):
        graph.append('.' + input().strip() + '.') # 양 끝 추가
    graph.append('.'*(W+2)) # 맨 아랫줄 추가
    
    prisoner = []
    # 죄수 위치 찾기
    for i in range(H+2):
        for j in range(W+2):
            if graph[i][j] == '$':
                prisoner.append((i,j))
                
    # 죄수1, 죄수2, 상근이의 위치로부터 3번의 BFS 실행
    one = BFS(prisoner[0][0],prisoner[0][1])
    two = BFS(prisoner[1][0],prisoner[1][1])
    sang = BFS(0,0)
    answer = sys.maxsize
    
    # 최소 횟수 구하기
    for i in range(H+2):
        for j in range(W+2):
            if one[i][j] != -1 and two[i][j] != -1 and sang[i][j] != -1:
                # 해당 위치에서 문을 여는 개수
                result = one[i][j] + two[i][j] + sang[i][j]  
                if graph[i][j] == '*': # 벽은 제외
                    continue
                if graph[i][j] == '#': # 한 명만 열어도 되기 때문에, 2를 빼줌
                    result -= 2
                answer = min(answer,result)            
    # Output            
    print(answer)            