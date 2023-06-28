# 입력 받음과 동시에 값 갱신하는 식으로 수정

import sys
input = sys.stdin.readline

# Input
N = int(input())
a,b,c = map(int,input().split())
max_,min_ = [a,b,c],[a,b,c] # 첫번째 값을 미리 저장

for _ in range(N-1):
    one,two,thr = map(int,input().split())
    
    max_0 = one + max(max_[0],max_[1])
    min_0 = one + min(min_[0],min_[1])
    max_1 = two + max(max_[0],max_[1],max_[2])
    min_1 = two + min(min_[0],min_[1],min_[2])
    max_2 = thr + max(max_[1],max_[2])
    min_2 = thr + min(min_[1],min_[2])
    
    max_ = [max_0,max_1,max_2]
    min_ = [min_0,min_1,min_2]
    
# Output
print(max(max_),min(min_))