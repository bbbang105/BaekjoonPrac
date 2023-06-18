import heapq
import sys
input = sys.stdin.readline

for T in range(int(input())):
    N = int(input())
    visited = [False] * N
    maxHQ,minHQ = [],[]
    for i in range(N):
        command,n = input().split()
        n = int(n)
        
        # 원소를 추가하는 연산
        if command == 'I':
            heapq.heappush(maxHQ,(-n,i))
            heapq.heappush(minHQ,(n,i))
            visited[i] = True # 존재함을 나타냄
            
        # 최댓값을 제거하는 연산   
        elif n == 1:
            # 이미 삭제된 노드를 버려주는 과정
            while maxHQ and not visited[maxHQ[0][1]]:
                heapq.heappop(maxHQ)
            if maxHQ:
                visited[maxHQ[0][1]] = False # 제거했음을 나타냄
                heapq.heappop(maxHQ)
         
        # 최솟값을 제거하는 연산        
        else:
            # 이미 삭제된 노드를 버려주는 과정
            while minHQ and not visited[minHQ[0][1]]:
                heapq.heappop(minHQ)
            if minHQ:
                visited[minHQ[0][1]] = False # 제거했음을 나타냄
                heapq.heappop(minHQ)
    
    # 마지막으로 버려야 할 노드가 있는지 확인       
    while maxHQ and not visited[maxHQ[0][1]]:
        heapq.heappop(maxHQ)
    while minHQ and not visited[minHQ[0][1]]:
        heapq.heappop(minHQ)
        
    # 출력
    print(-maxHQ[0][0],minHQ[0][0]) if maxHQ and minHQ else print('EMPTY')