import sys
input = sys.stdin.readline

N = int(input())
lst = [list(map(int,input().split())) for _ in range(N)]
lst.sort(key = lambda x : -x[1]) # 점수가 높은 순으로 정렬

answer = [0 for _ in range(1000)] # 최대 1000일의 과제가 존재

for i in range(N):
    limit = lst[i][0]
    score = lst[i][1]
    for j in range(limit-1,-1,-1):
        if answer[j] == 0:
            answer[j] = score
            break
        
print(sum(answer))