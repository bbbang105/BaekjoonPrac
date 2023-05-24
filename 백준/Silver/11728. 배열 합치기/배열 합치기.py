import sys
input = sys.stdin.readline

N,M = map(int,input().split())
arr1 = sorted(list(map(int,input().split())))
arr2 = sorted(list(map(int,input().split())))

# Two Pointers Algorithm
p1,p2 = 0,0

while True:
    if arr1[p1] < arr2[p2]:     # arr1의 수가 작은 경우
        print(arr1[p1],end=' ')
        p1 += 1
    elif arr1[p1] == arr2[p2]:  # arr1과 arr2의 수가 같은 경우
        print(arr1[p1],end=' ')
        print(arr2[p2],end=' ')
        p1 += 1
        p2 += 1
    else:                       # arr2의 수가 작은 경우
        print(arr2[p2],end=' ')
        p2 += 1
     
    if p1 == N:     # arr1이 모두 출력된 경우        
        for i in range(p2,M):
            print(arr2[i],end=' ')
        break
    elif p2 == M:   # arr2가 모두 출력된 경우
        for i in range(p1,N):
            print(arr1[i],end=' ')
        break