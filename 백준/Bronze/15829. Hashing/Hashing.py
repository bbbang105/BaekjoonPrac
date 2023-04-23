import sys
input = sys.stdin.readline

n = int(input())
alpha = input().rstrip()

sum = 0
for i in range(len(alpha)):
    sum += (ord(alpha[i])-96)*(31**i)
    
print(sum)