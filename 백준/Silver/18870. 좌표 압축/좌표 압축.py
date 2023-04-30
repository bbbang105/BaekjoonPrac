import sys
input = sys.stdin.readline

N = int(input())
lst = list(map(int,input().rstrip().split()))
compare = list(set(lst))
compare.sort()

def BS(num):
    start,end = 1, len(compare)-1
    while True:
        mid = (start+end) // 2
        if compare[mid] > num:
            end = (mid-1)
        else:
            start = (mid+1)
        if compare[mid] == num:
            break
    return mid

for i in lst:
    print(BS(i),end=' ')