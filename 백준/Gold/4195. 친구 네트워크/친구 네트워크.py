import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline

def find(n):
    if parent[n] == n:
        return n
    parent[n] = find(parent[n])
    return parent[n]

def union(a,b):
    a,b = find(a),find(b)
    if a != b:
        parent[b] = a # 부모 노드 변경
        num[a] += num[b] # 친구 수를 합쳐줌
 
T = int(input())
for _ in range(T):
    N = int(input())
    parent = dict()
    num = dict()
    
    for _ in range(N):
        person1,person2 = input().split()
        # 노드 지정
        if person1 not in parent:
            parent[person1] = person1
            num[person1] = 1
        if person2 not in parent:
            parent[person2] = person2
            num[person2] = 1
        # 지정한 노드로 union   
        union(person1,person2)
        # 친구 네트워크에 몇 명이 있는지 확인
        print(num[find(person1)])