import sys
input = sys.stdin.readline

def quadTree(x,y,n):
    global result
    first = video[x][y]  # 초기 숫자
    
    for i in range(x,x+n):
        for j in range(y,y+n):
            # 4등분 해야하는 경우
            if first != video[i][j]:
                result += '('
                quadTree(x, y, n//2)            # 1사분면   
                quadTree(x, y+n//2, n//2)       # 2사분면
                quadTree(x+n//2, y, n//2)       # 3사분면
                quadTree(x+n//2, y+n//2, n//2)  # 4사분면
                result += ')'
                return
    # 동일한 숫자만 나온 경우        
    if first == '0':
        result += '0'
    elif first == '1':
        result += '1'
    
if __name__ == '__main__':
    n = int(input())
    video = [list(input().rstrip()) for _ in range(n)]
    result = ''
    quadTree(0,0,n)
    print(result)