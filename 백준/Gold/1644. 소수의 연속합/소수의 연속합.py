import sys, math
input = sys.stdin.readline

def isPrimeNum(num): # 에라토스테네스의 체
    arr = [True for i in range(num+1)] # 우선 모두 True(=소수)라고 설정하고 시작
    
    for i in range(2,int(math.sqrt(num)+1)): # 제곱근까지만 반복해줌 
        if arr[i] == True: # 남아 있는 수 (=소수)인 경우
            j = 2
            while i*j <= num:
                arr[i*j] = False # 소수가 아님 (=제거)
                j += 1
    
    return [i for i in range(2,num+1) if arr[i]] # True (=소수)인 숫자들만 return

N = int(input())
if N == 1: # N이 1일 경우에는 무조건 0
    print(0)
    exit(0)
    
lst = isPrimeNum(N)

# 투 포인터 알고리즘
left,right = 0,1
cnt = 0
temp = lst[0]
while True:
    if temp < N:
        if right < len(lst):
            temp += lst[right]
            right += 1
        else:
            break
        
    elif temp == N:
        cnt += 1
        temp -= lst[left]
        left += 1
        
    else:
        temp -= lst[left]
        left += 1
        
print(cnt)