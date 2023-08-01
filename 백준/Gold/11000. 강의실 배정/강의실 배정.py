import sys, heapq
input = sys.stdin.readline

N = int(input())
table = [list(map(int,input().split())) for _ in range(N)]
table.sort()

res = 1
hq = []
heapq.heappush(hq,table[0][1])

for start,end in table[1:]:
    if start >= hq[0]:
        heapq.heappop(hq)
        heapq.heappush(hq,end)
    else:
        heapq.heappush(hq,end)
        res += 1

print(res)