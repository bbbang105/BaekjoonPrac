import sys
input = sys.stdin.readline

def BS(goal):
    start,end = 0,len(lst1)-1
    
    while start <= end:
        i = (start+end)//2
        num = lst1[i]
        if num == goal:
            return 1
        elif num > goal:
            end = (i-1)
        else:
            start = (i+1)
            
    return 0

T = int(input())
for _ in range(T):
    N = int(input())
    lst1 = sorted(list(map(int,input().split())))
    M = int(input())
    lst2 = list(map(int,input().split()))
    
    for i in lst2:
        print(BS(i))
        