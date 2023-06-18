from collections import deque
import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    A,B = map(int,input().split())
    visited = [False]*10000
    Q = deque()
    operation = ''
    Q.append((A,operation))
    while Q:
        n,operation = Q.popleft()
        
        if n == B:
            print(operation)
            break
            
        D_num = n*2 % 10000
        if not visited[D_num]:
            visited[D_num] = True
            Q.append((D_num,operation+'D'))
        
        S_num = n-1 if n != 0 else 9999
        if not visited[S_num]:
            visited[S_num] = True
            Q.append((S_num,operation+'S'))
        
        L_num = (n%1000)*10 + n//1000    
        if not visited[L_num]:
            visited[L_num] = True
            Q.append((L_num,operation+'L'))
        
        R_num = (n%10)*1000 + n//10
        if not visited[R_num]:
            visited[R_num] = True
            Q.append((R_num,operation+'R'))
