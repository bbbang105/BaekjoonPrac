import sys
from collections import deque
input = sys.stdin.readline

start,goal = map(int,input().split())
# 시작점이 더 큰 경우에는 -1 이동만
if start > goal:
    print(start-goal)
    print(*[i for i in range(start,goal-1,-1)])
    exit(0)
    
limit = max(start,goal)*2+1 # 한계치
visited = [False]*limit # 방문 여부
Q = deque()
Q.append([start]) # 시작점을 넣어줌

while Q:
    lst = Q.popleft()
    spot = lst[-1] # 현재 위치
    move = [spot*2,spot-1,spot+1] # 순간이동 *2, -1, +1
    for m in move:
        if 0 <= m < limit:
            if not visited[m]:
                visited[m] = True
                Q.append(lst+[m]) # 이동 경로 저장
    # 동생을 잡은 경우    
    if spot == goal:
        print(len(lst)-1) # 이동 시간
        print(*lst) # 이동 경로
        break