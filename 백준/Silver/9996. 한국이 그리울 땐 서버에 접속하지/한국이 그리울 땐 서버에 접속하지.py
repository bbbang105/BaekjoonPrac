import sys
input = sys.stdin.readline
N = int(input())
pattern = input().rstrip().split('*')
for _ in range(N):
    word = input().rstrip()
    t1 = False
    t2 = False
    # 패턴에 적합하는지 확인
    if word[:len(pattern[0])] == pattern[0]:
        t1 = True
        word = word[len(pattern[0]):]
    if word[-len(pattern[1]):] == pattern[-1]:
        t2 = True
    # 출력    
    if t1 and t2:
        print('DA')
    else:
        print('NE')