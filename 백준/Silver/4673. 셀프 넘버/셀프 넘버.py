import sys
input = sys.stdin.readline

self_num = [True] * 10001

for i in range(10001):
    num = i
    for j in str(i):
        num += int(j)
    if num <= 10000:
        self_num[num] = False
    
for k in range(10001):
    if self_num[k]:
        print(k, end = '\n')