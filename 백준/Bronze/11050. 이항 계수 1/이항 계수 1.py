from itertools import combinations
import sys
input = sys.stdin.readline

N,K = map(int,input().split())

arr = []
for i in range(N):
    arr.append(i)  
C = list(combinations(arr,K))

print(len(C))