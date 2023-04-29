import sys
import heapq
input = sys.stdin.readline

HQ = []
N = int(input())
for _ in range(N):
    cmd = int(input().rstrip())
    if cmd == 0:
        if HQ:
            print(heapq.heappop(HQ))
        else:
            print(0)
    else:
        heapq.heappush(HQ,cmd)