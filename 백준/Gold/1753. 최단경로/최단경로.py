import sys, heapq
input = sys.stdin.readline
INF = int(1e9)

# Input
V,E = map(int,input().split()) # 노드, 간선의 개수
start = int(input())  # 시작 노드
graph = [[] for _ in range(V+1)] # 간선 정보 테이블
dp = [INF]*(V+1) # 가중치 저장 테이블
heap = []

# 그래프 입력
for _ in range(E):
    u,v,w = map(int,input().split())
    # (가중치,노드) 순으로 저장
    graph[u].append((w,v))

# 다익스트라 알고리즘
def dijkstra(start):
    # 시작점 방문 처리
    dp[start] = 0
    # (가중치,노드) 순으로 힙에 삽입
    heapq.heappush(heap,(0,start))
    # 힙이 비게 될 때까지 반복
    while heap:
        # 현재 노드까지의 가중치, 현재 노드
        now_weight,now_node = heapq.heappop(heap)
        # 현재 테이블보다 가중치가 크면 무시
        if dp[now_node] < now_weight:
            continue
        # 현재 노드부터 다음 노드까지의 가중치, 다음 노드
        for next_weight,next_node in graph[now_node]:
            # 현재 노드를 거쳐서 가는 것이 더 빠르다면
            if (now_weight + next_weight) < dp[next_node]:
                dp[next_node] = (now_weight + next_weight)
                # 힙에 다음 노드까지의 가중치, 다음 노드를 삽입
                heapq.heappush(heap,(now_weight + next_weight,next_node))
                
# 실행
dijkstra(start)

# Output
for i in range(1,V+1):
    if dp[i] == INF:
        print('INF')
    else:
        print(dp[i])