from collections import deque
import sys
input = sys.stdin.readline

X = int(input())

visited = [False] * (X+1)
Q = deque()
Q.append((X,0))
while Q:
    q = Q.popleft()
    num = q[0]
    cnt = q[1]
    
    if num == 1:
        print(cnt)
        exit(0)
    
    if num % 3 == 0 and not visited[num // 3]:
        visited[num // 3] = True
        Q.append((num // 3, cnt + 1))
        
    if num % 2 == 0 and not visited[num // 2]:
        visited[num // 2] = True
        Q.append((num // 2, cnt + 1))
        
    if not visited[num - 1]:
        visited[num - 1] = True
        Q.append((num - 1, cnt + 1))