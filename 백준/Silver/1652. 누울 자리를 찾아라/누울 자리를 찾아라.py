import sys
input = sys.stdin.readline

N = int(input())

lst = []
for _ in range(N):
    lst.append(input().rstrip())

row_pos = 0                     # 가로로 가능한 방의 개수
col_pos = 0                     # 세로로 가능한 방의 개수
for i in range(len(lst)):
    row_cnt = 0                 # 연속을 파악하기 위함
    col_cnt = 0
    row_pre = False             # 벽이나 짐이랑 닿아있는 상태인지 확인
    col_pre = False
    for j in range(N):
        if lst[i][j] == '.':    # 가로의 방을 기준으로
            row_cnt += 1   
            if row_cnt == 1:    # 처음 나온 경우
                row_pre = True  
            if row_cnt == 2 and row_pre:    # 연속해서 나온 경우
                row_pos += 1
        else:                   # 끊긴 경우
            row_cnt = 0
            row_pre = False
            
        if lst[j][i] == '.':    # 세로의 방을 기준으로
            col_cnt += 1     
            if col_cnt == 1:    # 처음 나온 경우
                col_pre = True  
            if col_cnt == 2 and col_pre:    # 연속해서 나온 경우
                col_pos += 1
        else:                   # 끊긴 경우
            col_cnt = 0
            col_pre = False
            
print(row_pos,col_pos,sep=' ')
                