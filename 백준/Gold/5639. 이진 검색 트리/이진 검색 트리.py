import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
      
pre = []
while True:
    try:
        pre.append(int(input()))
    except:
        break

def post_order(start,end):
    if start > end:
        return
    mid = end + 1 # 모든 원소가 루트 노드보다 작은 경우를 고려
    
    # 본인보다 큰 원소를 찾는 과정
    for i in range(start+1,end+1):
        if pre[i] > pre[start]:
            mid = i 
            break
    # 재귀    
    post_order(start+1,mid-1) # 왼쪽 트리 탐색
    post_order(mid,end)       # 오른쪽 트리 탐색
    print(pre[start])         # 루트 노드 출력
        
post_order(0,len(pre)-1)