import sys
input = sys.stdin.readline

N,K = map(int,input().split())

people = list(str(i) for i in range(1,N+1)) # 사람들의 숫자를 저장

answer = []                                 # 답을 담아줄 list
i = K-1                                     # 시작 index
while(len(answer) < N):
    if i >= len(people):                    # index를 넘어가는 경우
        while i >= len(people):             # 현재 남아있는 사람들보다 index가 크지 않을 때까지 -
            i -= len(people)
    answer.append(people.pop(i))            # answer에 해당 숫자를 pop()하여 append()
    i += (K-1)                              # 본인은 pop()되었으므로 -1
 
a = ', '.join(answer)       
print(f'<{a}>')