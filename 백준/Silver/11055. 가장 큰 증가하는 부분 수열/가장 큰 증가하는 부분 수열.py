import sys
input = sys.stdin.readline

N = int(input())
lis = list(map(int,input().rstrip().split()))
mm = [x for x in lis]

for i in range(1, N):
    for j in range(i):
        if lis[i]>lis[j]:
            mm[i]=max(mm[i],mm[j]+lis[i])
print(max(mm))