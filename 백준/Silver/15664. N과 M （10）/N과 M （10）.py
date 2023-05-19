from itertools import permutations
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = list(map(int, input().split()))
for sort in sorted(list(set(permutations(allSort, M)))):
    temp = True
    for i in range(len(sort)-1):
        if sort[i] > sort[i+1]:
            temp = False
            break
    if temp:
        print(*sort)