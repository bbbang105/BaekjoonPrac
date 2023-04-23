from collections import deque
import sys
input = sys.stdin.readline

dq = deque()
for _ in range(int(input())):
    x = input().rstrip().split()
    # (1) push_front
    if x[0] == 'push_front':
        dq.appendleft(x[1])
    # (2) push_back
    elif x[0] == 'push_back':
        dq.append(x[1])
    # (3) pop_front
    elif x[0] == 'pop_front':
        if dq:
            print(dq.popleft())
        else:
            print(-1)
    # (4) pop_back
    elif x[0] == 'pop_back':
        if dq:
            print(dq.pop())
        else:
            print(-1)
    # (5) size
    elif x[0] == 'size':
        print(len(dq))  
    # (6) empty:
    elif x[0] == 'empty':
        if dq:
            print(0)
        else:
            print(1)
    # (7) front
    elif x[0] == 'front':
        if dq:
            print(dq[0])
        else:
            print(-1)
    # (8) back
    elif x[0] == 'back':
        if dq:
            print(dq[-1])
        else:
            print(-1)