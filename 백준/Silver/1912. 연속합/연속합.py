import sys
input = sys.stdin.readline
dp = [0] * 100001

N = int(input())
lst = list(map(int,input().split()))

res = -1001
temp = 0
for num in lst:
    temp = max(temp + num,num)
    res = max(res,temp)
    
print(res)