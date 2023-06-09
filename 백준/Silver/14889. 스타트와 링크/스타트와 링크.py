from itertools import combinations
import sys
input = sys.stdin.readline

N = int(input())
graph = [list(map(int,input().split())) for _ in range(N)]
members = list(range(N))
minNum = sys.maxsize

for r1 in combinations(members,N//2):

    r2 = list(set(members) - set(r1)) # 차집합을 이용하여 Link Team을 구함
    start_score,link_score = 0,0
    
    for r in combinations(r1,2):
        start_score += graph[r[0]][r[1]]
        start_score += graph[r[1]][r[0]]

    for r in combinations(r2,2):
        link_score += graph[r[0]][r[1]]
        link_score += graph[r[1]][r[0]]
        
    minNum = min(minNum, abs(start_score-link_score))
        
print(minNum)