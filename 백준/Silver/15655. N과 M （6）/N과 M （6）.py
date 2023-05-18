from itertools import combinations
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = sorted(list(map(int, input().split())))
for sort in combinations(allSort,M):
    print(*sort)