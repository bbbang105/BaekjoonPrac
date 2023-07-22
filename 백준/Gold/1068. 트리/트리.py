from collections import defaultdict
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

def search(root):
    global cnt
    
    if root != cut: # 제거 노드인 경우는 고려 X
        if root not in tree.keys(): # 본인이 부모 노드가 아니라면
            cnt += 1 # 리프 노드 개수 + 1
        elif tree[root]== [cut]:
            cnt += 1
        else:
            for next in tree[root]:
                search(next) # 자식 노드 탐색
        
    return cnt

N = int(input())
lst = input().split()
# defaultdict를 활용해 value를 리스트 형태로 바로 추가
tree = defaultdict(list)
for i in range(N):
    if lst[i] == '-1':
        root = str(i)
        continue 
    tree[lst[i]].append(str(i))
cut = input().strip() # 제거할 노드

cnt = 0 # 리프 노드의 개수
res = search(root) # 루트 노드 0부터 탐색 시작
print(res)