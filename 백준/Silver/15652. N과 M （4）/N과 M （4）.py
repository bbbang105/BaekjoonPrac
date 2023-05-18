from itertools import product
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
data = [i for i in range(1,N+1)]
for sort in product(data, repeat = M):
    temp = True
    for i in range(len(sort)-1):
        if sort[i] > sort[i+1]:
            temp = False
            break
    if temp:
        print(*sort)