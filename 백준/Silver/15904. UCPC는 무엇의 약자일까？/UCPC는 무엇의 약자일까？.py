import sys
input = sys.stdin.readline

W = input().rstrip()

U_temp = False
C1_temp = False
P_temp = False
C2_temp = False
for i in W:
    if i == 'U' and not U_temp and not C1_temp and not P_temp and not C2_temp:
        U_temp = True
    elif i == 'C' and U_temp and not C1_temp and not P_temp and not C2_temp:
        C1_temp = True
    elif i == 'P' and U_temp and C1_temp and not P_temp and not C2_temp:
        P_temp = True
    elif i == 'C' and U_temp and C1_temp and P_temp and not C2_temp:
        C2_temp = True
        
if U_temp and P_temp and C1_temp and C2_temp:
    print('I love UCPC')
else:
    print('I hate UCPC')