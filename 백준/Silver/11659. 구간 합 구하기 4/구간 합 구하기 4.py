import sys
input = sys.stdin.readline

N,M = map(int,input().split())
arr = list(map(int,input().split()))

s = [0]                     # 구간합 설정
for i in range(len(arr)):           
    s.append(s[i]+arr[i])       
    
for _ in range(M):
    i,j = map(int,input().split())
    print(s[j] - s[i-1])