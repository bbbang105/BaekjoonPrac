import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def dfs(x):
    global res
    visited[x] = True
    cycle.append(x)
    num = numbers[x]
    
    if visited[num]:
        if num in cycle:
            res.append(cycle[cycle.index(num):])
        return
    else:
        dfs(num)
    
T = int(input())
for _ in range(T):
    N = int(input())
    numbers = [0] + list(map(int,input().split()))
    visited = [True] + [False]*N
    res = []
    for i in range(1,N+1):
        if not visited[i]:
            cycle = []
            dfs(i)
            
    print(len(res))