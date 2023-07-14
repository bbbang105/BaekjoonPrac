import sys
input = sys.stdin.readline

def DFS(x,y,direction,cnt):

    if cnt == 5:     # 승자를 찾은 경우
        
        # 육목인지를 확인
        mx1,my1 = x+dx[direction],y+dy[direction]
        mx2,my2 = i-dx[direction],j-dy[direction]
        temp1,temp2 = True,True
        # 마지막 돌에서 육목 확인
        if 0 <= mx1 < 19 and 0 <= my1 < 19:
            if board[mx1][my1] == color:
                temp1 = False
        # 시작한 돌에서 육목 확인
        if 0 <= mx2 < 19 and 0 <= my2 < 19:
            if board[mx2][my2] == color:
                temp2 = False 
        # 앞 뒤 모두 육목이 아님을 확인한 경우
        if temp1 and temp2:
            print(color) # 승자 출력
            if direction in [1,3,5,7]: # 시작한 돌이 가장 왼쪽 돌인 경우
                print(i+1,j+1)   # 시작한 돌의 가로줄, 세로줄 출력
            else: # 마지막 돌이 가장 왼쪽 돌인 경우
                print(x+1,y+1)   # 마지막 돌의 가로줄, 세로줄 출력 
            exit(0)      # 승자를 출력 후 강제 종료
        
    nx = x + dx[direction]
    ny = y + dy[direction]
    if 0 <= nx < 19 and 0 <= ny < 19:
        if board[nx][ny] == color:
            DFS(nx,ny,direction,cnt + 1)

board = [list(map(int,input().split())) for _ in range(19)]
# 순서대로 0.상 1.하 2.좌 3.우 4.왼쪽위 5.오른쪽위 6.왼쪽아래 7.오른쪽 아래
dx = [-1,1,0,0,-1,-1,1,1]
dy = [0,0,-1,1,-1,1,-1,1]
# 모든 오목판 탐색
for i in range(19):
    for j in range(19):
        if board[i][j] == 1 or board[i][j] == 2: # 흑 or 백 인 경우
            color = board[i][j] # 돌의 색깔
            for n in range(8):  # 주변 탐색
                nx = i + dx[n]
                ny = j + dy[n]
                if 0 <= nx < 19 and 0 <= ny < 19:
                    if board[nx][ny] == color:
                        DFS(nx,ny,n,2) # n은 방향의 정보, 이미 하나를 찾았으므로 2개부터 시작
                        
print(0) # 모든 탐색을 마쳤지만 승자를 가릴 수 없는 경우(= 아무도 이기지 않은 경우)