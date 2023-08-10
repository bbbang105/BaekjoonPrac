import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

# 도킹 가능한 게이트를 찾는 함수
def find(n):
    # 처음 사용되는 게이트인 경우
    if root[n] == n:
        return n
    root[n] = find(root[n]) # union도 함께 진행해줌
    return root[n]

G = int(input())
P = int(input())
# 게이트 정보를 저장
root = [i for i in range(G+1)]
# 비행기 정보를 저장
plane = [int(input()) for _ in range(P)]

# 최대로 도킹 가능한 비행기 수를 찾아줌
res = 0
for p in plane:
    # 이용가능한 게이트를 찾아줌
    gate = find(p)
    # 더 이상 이용가능한 게이트가 없으므로 폐쇄
    if gate == 0:
        break
    # 비행기 한 대를 댔으므로 -1
    root[gate] -= 1
    res += 1
    
print(res)