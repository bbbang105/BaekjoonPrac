import sys
input = sys.stdin.readline

for _ in range(int(input())):
    N = int(input())
    lst = [0,1,1,1,2,2]
    
    if 1 <= N <= 5:
        print(lst[N])
        continue
    
    for i in range(6,N+1):
        num = (lst[i-1] + lst[i-5])
        lst.append(num)
        
    print(lst[-1])