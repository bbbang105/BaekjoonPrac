from itertools import permutations
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
data = [i for i in range(1,N+1)]
for sort in permutations(data,M):
    print(*sort)