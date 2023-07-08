import sys
input = sys.stdin.readline

def r(n):
    return int(n)+1 if n-int(n) >= 0.5 else int(n)

N = int(input())
if not N:
    print(0)
    exit(0)
lst = [int(input()) for _ in range(N)]
lst.sort()
cut = r(N*0.15)
lst = lst[cut:N-cut]
print(r(sum(lst)/len(lst)))