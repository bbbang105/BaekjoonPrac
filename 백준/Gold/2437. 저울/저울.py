import sys
input = sys.stdin.readline

N = int(input())
nums = (list(map(int,input().split())))
nums.sort() # 정렬

res = 1 # 최대값보다 1 큰 수
for num in nums:
    # 구간이 이어지지 않을 경우 
    if res < num:
        break
    # 구간 늘려주기
    res += num

print(res)