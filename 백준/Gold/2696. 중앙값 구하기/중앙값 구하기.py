import sys,heapq
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    
    lst = []
    for _ in range(N//10 + 1): # 10개씩 입력
        lst += list(map(int,input().split()))
    
    left,right = [],[] 
    res = []
    odd = False
    
    for i in range(N):
        num = lst[i]
        # 기존 두 힙큐의 길이가 같을 때 = 홀수번째 입력을 받았을 때
        if len(left) == len(right):
            heapq.heappush(left,(-num,num))
            odd = True     
        else:
            heapq.heappush(right,(num,num))
            odd = False
        # left에 항상 중앙값이 오도록 하는 과정
        if right and left[0][1] > right[0][0]:
            left_num = heapq.heappop(left)[1]
            right_num = heapq.heappop(right)[0]
            heapq.heappush(left,(-right_num,right_num))
            heapq.heappush(right,(left_num,left_num))
        # 홀수번째 입력인 경우 중앙값을 추가   
        if odd:
            res.append(left[0][1])
    # 중앙값 개수 출력
    print(len(res))
    # 10개씩 출력
    while len(res) >= 10:
        print(*res[:10])
        res = res[10:]
    if res:
        print(*res)