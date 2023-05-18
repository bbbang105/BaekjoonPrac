from itertools import product
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = sorted(list(map(int, input().split())))
for sort in product(allSort,repeat = M):
    print(*sort)