from collections import deque
import sys
input = sys.stdin.readline

people,Numlink = map(int,input().split())       # 유저의 수, 관계의 수

graph = [[] for i in range(people+1)]           # 그래프 초기화
for i in range(Numlink):                        # 그래프 생성
    a,b = map(int,input().split())
    graph[a] += [b]                             # 양방향 연결
    graph[b] += [a]

lst = []                                        # 케빈 베이컨 수 저장
for i in range(1,people+1):                 
    visited = [0]*(people+1)                    # 방문 여부 
    Q = deque([i])
    while Q:                                
        pn = Q.popleft()                        # 탐색할 번호
        for x in graph[pn]:
            if visited[x] == 0:                 # 처음 만나는 사람이면                
                Q.append(x)                     # 다음 탐색 대상으로 넣어줌
                visited[x] += (visited[pn]+1)   # 단계는 이전 단계의 +1
    lst.append(sum(visited)-visited[i])         # 본인을 제외한 단계수의 합
  
print(lst.index(min(lst))+1)