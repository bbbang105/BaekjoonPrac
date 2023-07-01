import sys,heapq
input = sys.stdin.readline
INF = sys.maxsize

# Input
V,E = map(int,input().split())
graph = [[] for _ in range(V+1)]

for _ in range(E):
    u,v,w = map(int,input().split())
    # 양방향이기에 두 가지 모두 넣어줌
    graph[u].append((w,v))
    graph[v].append((w,u))

# 다익스트라 알고리즘
def dijkstra(start):
    dp = [INF]*(V+1)
    dp[start] = 0
    heap = []
    heapq.heappush(heap,(0,start))
    
    temp1,temp2 = False,False
    while heap:
        now_weight,now_node = heapq.heappop(heap)
        
        if dp[now_node] < now_weight:
            continue
        for next_weight,next_node in graph[now_node]:
            if (now_weight + next_weight) < dp[next_node]:
                dp[next_node] = (now_weight + next_weight)
                heapq.heappush(heap,(now_weight + next_weight,next_node))
                
    return dp

# 무조건 지나야하는 정점
V1,V2 = map(int,input().split())

# 출발점이 각각 1, V1, V2일 때의 최단 거리 dp
origin_dp = dijkstra(1)
V1_dp = dijkstra(V1)
V2_dp = dijkstra(V2)

V1_path = origin_dp[V1] + V1_dp[V2] + V2_dp[V] # 1 -> V1 -> V2 -> V 순
V2_path = origin_dp[V2] + V2_dp[V1] + V1_dp[V] # 1 -> V2 -> V1 -> V 순

# Output
result = min(V1_path,V2_path)
print(result if result < INF else -1)