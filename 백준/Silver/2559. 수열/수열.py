import sys
input = sys.stdin.readline

N,K = map(int,input().split())
lst = list(map(int,input().split()))
left,right = 0,K                            # 초기 index 설정
maxTemp,num = sum(lst[:K]),sum(lst[:K])     # 초기 최대합과 구간합 설정

while right < N:
    num = (num - lst[left] + lst[right])    # 왼쪽은 차, 오른쪽은 합
    maxTemp = max(maxTemp, num)             # 크기 비교
    left += 1                               # 한 칸씩 옮김
    right += 1
    
print(maxTemp)