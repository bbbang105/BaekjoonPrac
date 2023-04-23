import sys
input = sys.stdin.readline

arr = []
for _ in range(int(input())):
    arr.append(list(map(int,input().split())))

for x,y in sorted(arr):
    print(x, y)