import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
# 사과 위치 추가
A = int(input())
apple_lst = []
for _ in range(A):
    a,b = map(int,input().split())
    apple_lst.append((a-1,b-1))
# 커맨드 추가
C = int(input())
command = deque()
for _ in range(C):
    command.append((input().split()))

dx,dy = [-1,1,0,0],[0,0,-1,1] # 상하좌우
head = 3 # 초기 머리 방향 오른쪽
change_time,direction = command.popleft() # 초기 변경 시간, 방향
snake = []
snake.append((0,0)) # 초기 위치
time = 1 
while True:
    x,y = snake[-1][0],snake[-1][1]
    nx,ny = x+dx[head],y+dy[head]
    if 0 <= nx < N and 0 <= ny < N:
        # 사과를 먹은 경우
        if (nx,ny) in apple_lst:
            # 꼬리는 그대로 머리만 옮겨줌
            snake.append((nx,ny))
            apple_lst.remove((nx,ny)) # 먹은 사과 제거
        # 자신을 만난 경우
        elif (nx,ny) in snake:
            # 게임 종료
            print(time)
            exit(0)
        # 그냥 이동하는 경우
        else:
            # 머리와 꼬리 둘 다 옮겨줌
            snake.append((nx,ny))
            snake.pop(0)
    # 벽을 만난 경우
    else:
        # 게임 종료
        print(time)
        exit(0)
    
    # 머리 방향을 바꿀 시간인 경우
    if time == int(change_time):
        # 현재 머리의 방향 = 상
        if head == 0:
            if direction == 'L':
                head = 2
            else:
                head = 3
        # 현재 머리의 방향 = 하
        elif head == 1:
            if direction == 'L':
                head = 3
            else:
                head = 2
        # 현재 머리의 방향 = 좌
        elif head == 2:
            if direction == 'L':
                head = 1
            else:
                head = 0
        # 현재 머리의 방향 = 우
        else:
            if direction == 'L':
                head = 0
            else:
                head = 1
        # 새로운 값을 꺼내줌
        if command:
            change_time,direction = command.popleft()
        
    time += 1