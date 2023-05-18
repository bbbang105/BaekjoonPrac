import sys
input = sys.stdin.readline

def cut(x,y,n):
    global white_cnt, blue_cnt
    first_Color = paper[x][y]   # 처음 색상
    
    for i in range(x,x+n):
        for j in range(y,y+n):
            # 색이 달라 다시 4등분 하는 경우
            if paper[i][j] != first_Color:
                cut(x, y, n//2)            # 1사분면
                cut(x, y+n//2, n//2)       # 2사분면
                cut(x+n//2, y, n//2)       # 3사분면
                cut(x+n//2, y+n//2, n//2)  # 4사분면
                return
            
    # 색이 동일한 경우
    if first_Color == 0:
        white_cnt += 1
    elif first_Color == 1:
        blue_cnt += 1
                
if __name__ == '__main__':
    n = int(input())    # 한 변의 길이
    paper = [list(map(int, input().split())) for _ in range(n)]
    white_cnt, blue_cnt = 0, 0  # 색종이의 개수
    cut(0, 0, n)        # 함수 실행
    print(white_cnt, blue_cnt, sep='\n')