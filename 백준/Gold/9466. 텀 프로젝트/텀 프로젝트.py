import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def dfs(x):
    global res
    visited[x] = True # 방문 확인
    cycle.append(x) # 사이클에 추가
    num = lst[x] # 다음 방문할 숫자로 변경
    # 이미 방문한 경우
    if visited[num] == True:
        # 팀이 형성된 경우
        if num in cycle:
            # 사이클인 구간부터만 팀으로 추가
            res += cycle[cycle.index(num):]
        return
    else:
        dfs(num)
    
T = int(input())
for _ in range(T):
    N = int(input())
    lst = [0] + list(map(int,input().split()))
    visited = [True] + [False]*N
    res = [] # 사이클로 팀이 된 인원을 저장
    for i in range(1,N+1):
        if not visited[i]:
            cycle = []
            dfs(i)
            
    print(N - len(res))              