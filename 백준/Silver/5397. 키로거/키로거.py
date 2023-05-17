import sys
input = sys.stdin.readline

for _ in range(int(input())):
    W = input().rstrip()
    left, right = [], []
    
    for s in W:
        # <1. 커서를 왼쪽으로 옮기는 경우>
        if s == '<' and left:
            right.append(left.pop())
        # <2. 커서를 오른쪽으로 옮기는 경우>    
        elif s == '>' and right:
            left.append(right.pop())
        # <3. 제거하는 경우>    
        elif s == '-' and left:
            left.pop()
        # <4. 추가하는 경우>    
        elif s.isalnum():
            left.append(s)
        
    left.extend(reversed(right))         
    print(''.join(left))