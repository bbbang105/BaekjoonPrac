import sys
input = sys.stdin.readline

S, P = map(int, input().split())            # 임의 문자열 길이 & 부분 문자열 길이
String = list(str(input().rstrip()))          # 임의로 만든 DNA
A, C, G, T = map(int, input().split())      # 조건

dic = {'A': 0, 'C': 0, 'G': 0, 'T': 0}      # 각 문자열 개수를 저장
left, right = 0, P-1                        # 구간 양 끝 index
arr = list(String[left:right])              # 초기 부분 문자열
for i in arr:
    dic[i] += 1                 
cnt = 0                 

# Sliding Window Algorithm
while right < S:

    # 구간 완성
    dic[String[right]] += 1 # 가장 오른쪽 원소 추가
    
    # 계산
    if dic['A'] >= A  and dic['C'] >= C and dic['G'] >= G and dic['T'] >= T:
        cnt += 1

    # 구간이동
    dic[String[left]] -= 1 # 가장 왼쪽 원소 제거
    left += 1
    right += 1

print(cnt)