from itertools import permutations
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = list(map(int, input().split()))
for sort in sorted(list(set(permutations(allSort, M)))):
    print(*sort)
