import sys

n, m = list(map(int, sys.stdin.readline().split()))

truth = list(map(int, sys.stdin.readline().split()))[1:]

KNOW_TRUTH = 0
# 유니온 파인드용 부모 리스트. 여기서 0번은 사람으로 사용하지 않고, 진실을 아는 사람으로 친다.
parent = [i for i in range(n + 1)]
for person in truth:
    parent[person] = KNOW_TRUTH


# union find 를 하는 이유는 서로 겹치지 않는 그룹 (거짓말을 모르는 그룹과 거짓말을 아는 그룹)으로 나누기 위해서다.
def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


parties = []
for _ in range(m):
    party = list(map(int, sys.stdin.readline().split()))[1:]
    # 파티의 참석한 사람들에 대해 2명씩 union 해본다.
    for i in range(len(party) - 1):
        union(party[i], party[i + 1])
    parties.append(party)

answer = 0

for party in parties:
    know = False
    for i in range(len(party)):
        # 진실을 알고 있던 그룹에 속했었을 경우
        if find(party[i]) == KNOW_TRUTH:
            know = True
            break
    if not know:
        answer += 1

print(answer)