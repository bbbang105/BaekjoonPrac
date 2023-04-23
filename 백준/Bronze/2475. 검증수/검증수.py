import sys
input = sys.stdin.readline

N = list(map(int,input().split()))

sum = 0
for i in N:
    sum += i*i
    
print(sum%10)