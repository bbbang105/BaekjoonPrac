import sys
input = sys.stdin.readline

x,y,k = map(int,input().split())
A = [list(map(int,input().split())) for _ in range(3)]
R,C = 3,3
x-=1; y-=1
time = 0
while True:
    # 찾은 경우
    if x < R and y < C:
        if A[x][y] == k:
            print(time)
            break
    # 연산
    max_length = 0
    new = []
    # R연산
    if R >= C:
        for row in A:
            lst = []
            # 개수를 세어 lst에 저장한 후, 정렬
            for n in (set(row)-{0}):
                lst.append([n,row.count(n)])
            lst = sorted(lst,key=lambda x : (x[1],x[0]))
            # 현재는 2차원 배열이기 때문에, 풀어서 다시 넣어줌
            new2 = []
            for a,b in lst:
                new2.append(a); new2.append(b)
            new.append(new2)
            # 최장 길이 갱신
            max_length = max(max_length,len(new2))
        # 새로운 배열들의 길이를 맞추어 다시 저장
        for r in range(len(new)):
            gap = 0 # 최장 배열 길이와의 차이
            if len(new[r]) < max_length:
                gap = (max_length - len(new[r]))
            new_lst = new[r] + [0]*gap
            # 100개를 넘어가면 나머지 버림
            if len(new_lst) > 100:
                new_lst = new_lst[:100]
            A[r] = new_lst
        # R연산이므로, C의 길이가 늘어남  
        C = max_length
    # C연산        
    else:
        for c in range(C):
            col,lst = [],[]
            # 열 원소 저장
            for r in range(R):
                col.append(A[r][c])
            # 개수를 세어 lst에 저장한 후, 정렬
            for n in (set(col)-{0}):
                lst.append([n,col.count(n)])
            lst = sorted(lst,key=lambda x : (x[1],x[0]))
            # 현재는 2차원 배열이기 때문에, 풀어서 다시 넣어줌
            new2 = []
            for a,b in lst:
                new2.append(a); new2.append(b)
            new.append(new2)
            # 최장 길이 갱신
            max_length = max(max_length,len(new2)) 
        # 비워진 공간에 0을 추가하는 과정
        for r in range(len(new)):
            gap = 0
            if len(new[r]) < max_length:
                gap = (max_length - len(new[r]))
                new[r] = new[r] + [0]*gap
        # 행렬이 반대로 되어 있기에, 다시 맞춰주는 과정
        new_lst = []
        for c in range(max_length):
            re = []
            for r in range(C):
                re.append(new[r][c])
            # 100개를 넘어가면 나머지 버림
            if len(re) > 100:
                re = re[:100]
            new_lst.append(re)
        # C연산이므로, R의 길이가 늘어남  
        R = max_length
        A = new_lst
    # 시간 추가          
    time += 1
    # 100초 안에 찾지 못하는 경우
    if time > 100:
        print(-1)
        break