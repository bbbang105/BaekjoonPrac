import sys
input = sys.stdin.readline

n = (int(input()) * 2) -1

if n == 1:
    print('*')
    exit(0)
    
for i in range(n,0,-2):
    strip = (n-i)//2
    print(' '*strip + '*'*i)
for i in range(3,n+1,2):
    strip = (n-i)//2
    print(' '*strip + '*'*i)