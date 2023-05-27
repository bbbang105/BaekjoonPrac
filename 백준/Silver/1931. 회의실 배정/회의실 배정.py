import sys
input = sys.stdin.readline

N = int(input())
lst = sorted([list(map(int,input().split())) for _ in range(N)], key = lambda x : (x[1],x[0]))
cnt,end = 0,0

for s,e in lst:
    if s >= end:
        cnt += 1
        end = e
print(cnt)
