import sys
input = sys.stdin.readline

n = (int(input())*2) - 1
for i in range(n,0,-2):
    strip = (n-i)//2
    print(' '*strip + '*'*i)