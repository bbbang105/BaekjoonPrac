import sys
input = sys.stdin.readline

n = int(input())
ln = len(str(n))
ans = 0

for i in range(1,ln):
    ans += i * (pow(10,i) - pow(10,i-1))
ans += (n - pow(10,ln-1)+1) * (ln)

print(ans)