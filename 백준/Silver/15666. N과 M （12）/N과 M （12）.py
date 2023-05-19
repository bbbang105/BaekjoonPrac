N,M=map(int,input().split())

lst=list(map(int,input().split()))
dup_check=[]
def solve(num,start,ans):
    if len(ans)==num:
        
        for i in ans:
            print(lst[i],end=' ')
        print()
        
        return
    
    for i in range(start,len(lst)):
        solve(num,i,ans+[i])
lst=list(set(lst))
lst.sort()
for i in range(len(lst)):
    solve(M,i,[i])