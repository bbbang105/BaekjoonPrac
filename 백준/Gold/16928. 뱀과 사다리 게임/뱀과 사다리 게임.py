from collections import deque
import sys
input = sys.stdin.readline

def BFS(start):
    Q.append(start)
    visited[start] = 0
    while Q:
        x = Q.popleft()
        find = False
        for i in range(1,7):
            # 사다리 or 뱀이 있는 경우
            if lst[x+i] and not visited[lst[x+i]]:
                num = lst[x+i]
                Q.append(num)
                visited[num] = visited[x] + 1
                
            # 사다리 or 뱀이 없는 경우   
            elif not lst[x+i] and not visited[x+i]:
                Q.append(x+i)
                visited[x+i] = visited[x] + 1
                
            # 100까지 도달한 경우
            if (x+i) == 100:
                return visited[100] 

N,M = map(int,input().split())
lst = list(0 for _ in range(101))
# 사다리와 뱀 리스트에 입력
for _ in range(N+M):
    a,b = map(int,input().split())
    lst[a] = b
# 방문 여부
visited = [0 for _ in range(101)]
Q = deque()
print(BFS(1))