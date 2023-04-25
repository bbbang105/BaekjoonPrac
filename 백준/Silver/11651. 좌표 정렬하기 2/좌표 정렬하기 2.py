import sys
input = sys.stdin.readline

arr = []
for _ in range(int(input())):
    new = list(map(int,input().rstrip().split()))
    arr.append(new)
 
arr = sorted(arr, key = lambda x : (x[1], x[0]))

for x,y in arr:
    print(x, y)