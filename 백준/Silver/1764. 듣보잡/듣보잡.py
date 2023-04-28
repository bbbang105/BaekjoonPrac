import sys
input = sys.stdin.readline

N,M = map(int,input().split())

NoL = set([])
NoS = set([])

for i in range(N):
    NoL.add(input().rstrip())
for i in range(M):
    NoS.add(input().rstrip())

inter = NoL & NoS
print(len(inter),*sorted(inter),sep='\n')