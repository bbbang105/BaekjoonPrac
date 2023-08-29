import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
if N == 1:
    print(0,1,sep = '\n')
    exit(0)
    
visited = [False]*(N+1)
Q = deque()
Q.append([N])
while Q:
    lst = Q.popleft()
    x = lst[-1]
    n1,n2,n3 = x/3, x/2, x-1
    if n1 == 1 or n2 == 1 or n3 ==1:
        lst += [1]
        print(len(lst)-1)
        print(*lst) 
        break
    
    n1 = int(n1); n2 = int(n2)
    if x%3 == 0 and not visited[n1]:
        visited[n1] = True
        Q.append(lst+[n1])
    if x%2 == 0 and not visited[n2]:
        visited[n2] = True
        Q.append(lst+[n2])
        
    visited[n3] = True
    Q.append(lst+[n3])