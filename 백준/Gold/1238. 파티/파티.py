import sys,heapq
input = sys.stdin.readline
INF = sys.maxsize

# Input
N,M,X = map(int,input().split()) # 노드, 간선의 개수, 시작점
graph_pro = [[] for _ in range(N+1)]   # (파티가 열리는 마을 => 본인의 마을) 간선 정보
graph_retro = [[] for _ in range(N+1)] # (본인의 마을 => 파티가 열리는 마을) 간선 정보

for _ in range(M):
    u,v,w = map(int,input().split())
    graph_pro[u].append((w,v)) # (가중치,도착 노드) 순 저장
    graph_retro[v].append((w,u)) # (가중치,출발 노드) 순 저장

# 다익스트라 알고리즘   
def dijkstra(start,g):
    graph = g
    dp = [INF]*(N+1) # 가중치를 저장
    dp[start] = 0
    heap = []
    heapq.heappush(heap,(0,start))
    
    while heap:
        now_weight,now_node = heapq.heappop(heap)
        
        if dp[now_node] < now_weight: # 무시해도 되는 경우
            continue
        
        for next_weight,next_node in graph[now_node]:
            # 현재 노드를 거쳐서 가는 것이 더 빠르다면
            if now_weight + next_weight < dp[next_node]:
                dp[next_node] = (now_weight + next_weight)
                heapq.heappush(heap,((now_weight + next_weight),next_node))
                
    return dp

# 실행               
way_to_myhome = dijkstra(X,graph_pro)   # 파티가 열리는 마을 => 본인의 마을까지의 최단 거리 중 가장 큰 수
way_to_party = dijkstra(X,graph_retro)  # 본인의 마을 => 파티가 열리는 마을까지의 최단 거리 중 가장 큰 수

# Output
max_distance = 0
# 가장 오래 걸리는 학생의 소요시간 출력
for i in range(1,N+1):
    distance = way_to_myhome[i] + way_to_party[i]
    max_distance = max(max_distance,distance)

print(max_distance)