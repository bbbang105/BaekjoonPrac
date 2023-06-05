import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    lst = [list(map(int,input().split())) for _ in range(N)]
    lst = sorted(lst)
    
    minNum = lst[0][1]
    cnt = 1
    for person in lst[1:]:
        if person[1] < minNum:
            minNum = person[1]
            cnt += 1
    print(cnt)