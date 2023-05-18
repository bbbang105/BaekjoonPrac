import sys
input = sys.stdin.readline

n = int(input())

for i in range(1,n):
    print('*'*i + ' '*((n-i)*2) + '*'*i)
print('*'*(n*2))
for i in range(n-1,0,-1):
    print('*'*i + ' '*((n-i)*2) + '*'*i)