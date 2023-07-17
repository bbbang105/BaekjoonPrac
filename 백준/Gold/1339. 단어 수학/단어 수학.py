import sys
input = sys.stdin.readline

N = int(input())

# 입력받은 알파벳을 숫자로 바꾸어 리스트에 저장
word = [list(map(lambda x : ord(x)-65, input().strip())) for _ in range(N)]

# 알파벳 리스트 생성
alpha = [0] * 26

# 입력받은 알파벳 순서대로 반복
for i in range(N):
    j = 0
    # 각 알파벳의 끝 자리부터 10^j를 더해줌 (자리수별 가중치)
    for w in word[i][::-1]:
        alpha[w] += (10 ** j)
        j += 1
        
# 9부터 곱해주기 위해서, 내림차순 정렬    
alpha.sort(reverse = True)

# 결과값, 곱해주는 값
res,multi = 0,9

for i in range(26):
    # 뒷 배열도 모두 0일 것이므로
    if alpha[i] == 0:
        break
    res += (alpha[i] * multi)
    multi -= 1
    
print(res)
