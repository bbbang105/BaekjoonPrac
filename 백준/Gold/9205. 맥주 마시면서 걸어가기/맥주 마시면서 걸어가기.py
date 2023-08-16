import sys
from collections import deque
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    x,y = map(int,input().split())
    cs = []
    for _ in range(N):
        a,b = map(int,input().split())
        cs.append([a,b])
    goal_x,goal_y = map(int,input().split())

    cs.sort() 
    del_lst = []
    Q = deque()
    Q.append((x,y))
    temp = False
    while Q:
        x,y = Q.popleft()
        if abs(goal_x-x) + abs(goal_y-y) <= 1000:
            temp = True
            break
        for nx,ny in cs:
            if abs(nx-x) + abs(ny-y) <= 1000 and (nx,ny) not in del_lst:
                del_lst.append((nx,ny))
                Q.append((nx,ny))
                
    if temp:
        print('happy')
    else:
        print('sad')