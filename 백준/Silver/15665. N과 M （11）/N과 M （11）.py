from itertools import product
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = list(map(int, input().split()))
for sort in sorted(list(set(product(allSort, repeat = M)))):
    print(*sort)