import heapq,sys
input = sys.stdin.readline

N = int(input())
left_hq,right_hq = [],[]

for _ in range(N):
    num = int(input())
    
    # 길이가 같을 경우, 최대힙(=left heap)에 값을 추가
    if len(left_hq) == len(right_hq):
        heapq.heappush(left_hq,(-num,num))
    # 길이가 다른 경우, 최소힙(=right heap)에 값을 추가
    else:
        heapq.heappush(right_hq,(num,num))

    # left heap에는 중간값보다 작은 값만이 오게하는 과정
    if right_hq and left_hq[0][1] > right_hq[0][0]:
        left_num = heapq.heappop(left_hq)[1]
        right_num = heapq.heappop(right_hq)[0]
        heapq.heappush(left_hq,(-right_num,right_num))
        heapq.heappush(right_hq,(left_num,left_num))
        
    print(left_hq[0][1])