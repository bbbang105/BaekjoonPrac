import sys,heapq
input = sys.stdin.readline
INF = sys.maxsize

V = int(input())
E = int(input())
graph = [[] for _ in range(V+1)]
dp = [INF]*(V+1)
heap = []

for _ in range(E):
    u,v,w = map(int,input().split())
    graph[u].append((w,v))
    
def dijkstra(start):
    dp[start] = 0
    heapq.heappush(heap,(0,start))
    while heap:
        now_weight,now_node = heapq.heappop(heap)
        if dp[now_node] < now_weight:
            continue
        for next_weight,next_node in graph[now_node]:
            distance = (now_weight + next_weight) # 현재 노드를 거쳐 다음 노드로 갈 때의 가중치
            if distance < dp[next_node]:
                dp[next_node] = distance
                heapq.heappush(heap,(distance,next_node))
                
start,goal = map(int,input().split())
dijkstra(start)
print(dp[goal])
                