import sys,heapq
input = sys.stdin.readline

N,K = map(int,input().split()) # 보석, 가방의 개수

# 리스트에 보석과 가방 정보를 함께 저장
lst = []
bag = 1000001 # 가방임을 나타냄 (=보석의 최대 가치보다 커야 동일한 무게에서 뒤쪽에 위치)
for _ in range(N):
    lst.append(list(map(int,input().split())))
for _ in range(K):
    lst.append([int(input()),bag]) 
# 무게가 가벼운 순으로 정렬  
lst.sort()
# 현재 가방에 들어갈 수 있는 가장 높은 보석의 가치가 힙큐 맨 앞에 위치
hq = []
res = 0
for info in lst:
    if info[1] != bag:
        heapq.heappush(hq,-info[1])
    else:
        if hq:
            res += (-heapq.heappop(hq))
        
print(res)