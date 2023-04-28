import sys
input = sys.stdin.readline

N,M = map(int,input().split())

PWdct = dict()
for i in range(N):
    a = input().rstrip().split()
    PWdct[a[0]] = a[1]
    
for i in range(M):
    print(PWdct[input().rstrip()])