'''
BJ 12891 DNA 비밀번호

예를 들어, 'GATACG'라는 문자열이 있고 (st = 'GATACG'), 비밀번호는 4자리일 때 (pw_len = 4)
맨 처음에 GAT를 카운트한다. G 1번, A 1번, T 1번 나왔다. -> initial setting
stCount = [1, 0, 1, 1]  # A C G T
GATA는 G 1번, A 1번, T 1번 + A가 1번 나온 것이다.
stCount = [2, 0, 1, 1]
이것과 requiredCount 를 비교한다.
두 번째 4자리 비밀번호는 ATAC인데 이걸 카운팅할 때는 stCount에 C 인덱스에 1을 더하고, G 인덱스에 1을 빼주면 된다.
stCount = [2, 1, 0, 1]
이것과 requiredCount 를 비교한다.
즉, 중복되는 부분은 카운트 하지 않고, 옆으로 슬라이딩할 때 새로 나오는 문자열의 인덱스를 카운트하고,
이전 문자열의 맨 첫 인덱스를 -1 해주는 이런 로직으로 풀어보았다.

Memory: 33196KB, Time: 824ms

ex.1)
input:
9 8
CCTGGATTG
2 0 1 1
output:
0

ex.2)
input:
4 2
GATA
1 0 0 1
output:
2

'''

n, pw_len = map(int, input().split())
st = input()
requiredCount = list(map(int, input().split()))

stList = ['A', 'C', 'G', 'T']
stCount = [0] * len(stList)

possible = 0

# initial setting
for i in range(pw_len-1):
    stCount[stList.index(st[i])] += 1

# Check
for i in range(n - pw_len + 1):
    stCount[stList.index(st[pw_len-1+i])] += 1
    temp = 0
    for j in range(4):
        if requiredCount[j] > stCount[j]:
            break
        else:
            temp += 1
    if temp == 4: possible += 1
    stCount[stList.index(st[i])] -= 1

print(possible)


""" 
시간초과 풀이: 3개의 for 문을 중첩했음
n, pw_len = map(int, input().split())
st = input()
a_num, c_num, g_num, t_num = map(int, input().split())
temp = ''
possible = 0

for i in range(n-pw_len+1):
    for j in range(i, i+pw_len):
        temp += st[j]
    # print(temp, end=' ')
    a_count, c_count, g_count, t_count = 0, 0, 0, 0
    for k in temp:
        if k == 'A': a_count +=1
        elif k == 'C': c_count += 1
        elif k == 'G': g_count += 1
        elif k == 'T': t_count += 1
    if a_count >= a_num and c_count >= c_num and g_count >= g_num and t_count >= t_num:
        possible += 1
    temp = ''
    # print()
print(possible)
"""