import sys
input = sys.stdin.readline

def paintingStar(size):
    if size == 1:
        return ['*']
    
    stars = paintingStar(size//3)
    
    L = []
    for s in stars:
        L.append(s*3)
    for s in stars:
        L.append(s + ' '*(size//3) + s)
    for s in stars:
        L.append(s*3)
    return L
    
if __name__ == '__main__':
    n = int(input())
    print('\n'.join(paintingStar(n)))