import sys,heapq
input = sys.stdin.readline
INF = sys.maxsize

# Input
V = int(input())
E = int(input())
graph = [[] for _ in range(V+1)]
dp = [INF]*(V+1)
parent = [0]*(V+1)
heap = []

for _ in range(E):
    u,v,w = map(int,input().split())
    graph[u].append((w,v))

# dijkstra  algorithm  
def dijkstra(start):
    dp[start] = 0
    heapq.heappush(heap,(0,start))
    while heap:
        now_weight,now_node = heapq.heappop(heap)
        if dp[now_node] < now_weight:
            continue
        for next_weight,next_node in graph[now_node]:
            distance = now_weight + next_weight
            if distance < dp[next_node]:
                dp[next_node] = distance
                # 부모 노드를 저장
                parent[next_node] = now_node
                     
                heapq.heappush(heap,(distance,next_node))

# Output                
start,goal = map(int,input().split())
dijkstra(start)
result = []
# 부모 노드를 통해 경로 역추적
temp = goal
while temp:
    result.append(temp)
    temp = parent[temp]
result = result[::-1]
             
print(dp[goal])     # 최소 가중치
print(len(result))  # 거쳐간 노드들의 개수
print(*result)      # 거쳐간 노드들의 정보