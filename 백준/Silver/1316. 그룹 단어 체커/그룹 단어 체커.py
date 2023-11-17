import sys
input = sys.stdin.readline

res = 0
for _ in range(int(input())):
    word = input().strip()

    group_word = word[0]
    word_lst = [word[0]]
    flag = True
    for w in word[1:]:
        if group_word == w:
            continue
        
        if w not in word_lst:
            word_lst += w
            group_word = w
        else:
            flag = False
            break
        
    if flag:
        res += 1
        
print(res)