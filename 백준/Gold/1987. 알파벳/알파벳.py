import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

R,C = map(int,input().split())
graph = [input().strip() for _ in range(R)]
check = [0]*91 # ord 메소드를 이용하기 위한 배열 생성

def DFS(x,y,cnt):
    global max_cnt
    
    if cnt > max_cnt: # 값 갱신
        max_cnt = cnt
        
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < R and 0 <= ny < C:
            if not check[ord(graph[nx][ny])]: # 아직 지나지 않은 알파벳인 경우
                check[ord(graph[nx][ny])] = 1 # 지난 알파벳으로 추가
                DFS(nx,ny,cnt + 1)
                check[ord(graph[nx][ny])] = 0 # 해당 위치에서의 탐색이 종료되었으므로, 다시 지나지 않은 알파벳으로 변경

dx = [1,-1,0,0]                
dy = [0,0,-1,1]
check[ord(graph[0][0])] = 1 # 첫 알파벳
max_cnt = 0
DFS(0,0,1)
print(max_cnt)