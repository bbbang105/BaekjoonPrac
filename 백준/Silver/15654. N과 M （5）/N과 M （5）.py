from itertools import permutations
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = sorted(list(map(int, input().split())))
for sort in permutations(allSort,M):
    print(*sort)