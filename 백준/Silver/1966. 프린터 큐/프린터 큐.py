import sys
input = sys.stdin.readline

for _ in range(int(input())):
    n,i = map(int, input().split())        # 문서의 개수, 순서가 궁금한 문서의 index
    L = list(map(int, input().split()))    # 문서의 중요도 list
    
    c = 0                                  # 순서를 count
    while True:
        if L[0] < max(L):                  # 본인보다 큰 수가 있는 경우
            L.append(L.pop(0))             
            if i == 0:                     # 궁금한 문서가 뒤로 빠질 경우
                i += (len(L)-1)
            else:
                i -= 1
                
        else:                              # 본인이 나갈 경우
            if i == 0:
                c += 1
                print(c)
                break
            L.pop(0)
            i -= 1
            c += 1