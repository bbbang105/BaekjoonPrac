import sys
input = sys.stdin.readline

N,M = map(int,input().split())
A = [list(map(int,input().split())) for _ in range(N)]
M,K = map(int,input().split())
B = [list(map(int,input().split())) for _ in range(M)]

res = [[0]*K for _ in range(N)]
for r in range(N):
    for c in range(K):
        for i in range(M):
            res[r][c] += A[r][i] * B[i][c]

for row in res:
       print(*row)
