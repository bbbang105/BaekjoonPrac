import sys
input = sys.stdin.readline
# 입력
N,B = map(int,input().split())
matrix = [list(map(int,input().split())) for _ in range(N)]

# 행렬 곱 함수
def multi_matrix(mat1,mat2):
    res = [[0]*N for _ in range(N)]
    for r in range(N):          # mat1에서의 행
        for c in range(N):      # mat2에서의 열
            for i in range(N):  # 각 행 or 열에서의 원소 위치
                res[r][c] += mat1[r][i] * mat2[i][c] % 1000
                
    return res
# 고속 거듭제곱 함수
def power(mat,n):
    if n == 1:     # n이 1이 될 때까지 재귀
        return mat # 입력받은 초기의 행렬
    else:
        temp = power(mat,n//2) # mat^(n//2)
        if n % 2 == 0: # 지수가 짝수인경우
            return multi_matrix(temp,temp)
        else:          # 지수가 홀수인경우
            return multi_matrix(multi_matrix(temp,temp),mat)

# 출력            
answer = power(matrix,B)

for r in answer:
    for c in r:
        print(c % 1000, end = ' ')
    print()