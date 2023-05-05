import sys
input = sys.stdin.readline

W = input().rstrip()

if W == W[::-1]:                # 이미 팰린드롬인 경우
    print(len(W))
    
else:
    for i in range(len(W)):
        if W[i] == W[-1]:
            l = len(W[i:])
            if l % 2 == 0:      # 팰린드롬의 길이가 짝수인 경우
                half = l//2
                re = (W[i+half:])
                if W[i:i+half] == re[::-1]:
                    print(len(W)+i)
                    break
            else:               # 팰린드롬의 길이가 홀수인 경우
                half = l//2
                if W[i:i+half] == W[i+half+1:][::-1]:
                    print(len(W)+i)
                    break
        if i == (len(W)-1):     # 전체 문자열을 새로 짜야 하는 경우
            print(i*2+1)