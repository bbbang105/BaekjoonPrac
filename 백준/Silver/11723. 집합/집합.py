import sys
input = sys.stdin.readline

s = set([])
for _ in range(int(input())):
    cmd = input().split()
    
    if cmd[0] == 'add' and int(cmd[1]) not in s:
        s.add(int(cmd[1]))
    elif cmd[0] == 'remove' and int(cmd[1]) in s:
        s.remove(int(cmd[1]))
    elif cmd[0] == 'check':
        if int(cmd[1]) in s:
            print(1)
        else:
            print(0)
    elif cmd[0] == 'toggle':
        if int(cmd[1]) in s:
            s.remove(int(cmd[1]))
        else:
            s.add(int(cmd[1]))
    elif cmd[0] == 'all':
        s = set([i for i in range(1,21)])
    else:
        s.clear()