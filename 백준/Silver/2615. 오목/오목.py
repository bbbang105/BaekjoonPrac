import sys
input = sys.stdin.readline

board = [list(map(int,input().split())) for _ in range(19)]
# → ↓ ↘ ↗ 방향으로 이동
dx = [0,1,1,-1]
dy = [1,0,1,1]

for x in range(19):
    for y in range(19):
        if board[x][y] != 0:
            color = board[x][y]
            
            for i in range(4):
                cnt = 1 # 연속된 같은 돌의 개수
                nx = x + dx[i]
                ny = y + dy[i]

                while 0 <= nx < 19 and 0 <= ny < 19 and board[nx][ny] == color:
                    cnt += 1
                    # 육목 체크
                    if cnt == 5:
                        # 첫번째 돌에서 하나 이전 돌을 확인
                        if 0 <= x - dx[i] < 19 and 0 <= y - dy[i] < 19 and board[x - dx[i]][y - dy[i]] == color:
                            break
                        # 마지막 돌에서 하나 이후 돌을 확인
                        if 0 <= nx + dx[i] < 19 and 0 <= ny + dy[i] < 19 and board[nx + dx[i]][ny + dy[i]] == color:
                            break
                        # 육목도 아님을 확인하여, 최종적으로 승부가 난 경우
                        print(color)   # 흑 or 백을 출력
                        print(x+1,y+1) # 첫째돌의 가로,세로줄 번호를 출력
                        exit(0)
                    
                    # 일정한 방향으로 계속 진행
                    nx += dx[i]
                    ny += dy[i]
# 승부가 나지 않은 경우
print(0)