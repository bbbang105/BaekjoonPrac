from itertools import product
import sys
input = sys.stdin.readline

N,M = map(int, input().split())
data = [i for i in range(1,N+1)]
for sort in product(data, repeat = M):
    print(*sort)