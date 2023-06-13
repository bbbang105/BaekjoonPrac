import sys
input = sys.stdin.readline

N = int(input())
lst = sorted(list(map(int,input().split())))
budget = int(input())
n_lst = range(1,lst[-1]+1)

start,end = 0,len(n_lst)-1
while start <= end:
    mid = (start+end)//2
    limit = n_lst[mid]
    money = 0
    for n in lst:
        if n < limit:
            money += n
        else:
            money += limit
            
    if money < budget:
        start = (mid+1)
    elif money > budget:
        end = (mid-1)
    else:
        print(n_lst[mid])
        exit(0)
 
if money > budget:        
    print(n_lst[mid-1])
else:
    print(n_lst[mid])
