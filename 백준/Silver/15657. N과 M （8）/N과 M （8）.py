from itertools import product
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
allSort = sorted(list(map(int, input().split())))
for sort in product(allSort,repeat = M):
    temp = True
    for i in range(len(sort)-1):
        if sort[i] > sort[i+1]:
            temp = False
            break
    if temp:
        print(*sort)