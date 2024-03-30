# 조합
answer = 0

def comb(cnt, start, number, result):
    global answer
    if cnt == 3:
        print(result)
        if sum(result) == 0:
            answer += 1
        return

    for i in range(start, len(number)):
        result[cnt] = number[i]
        comb(cnt + 1, i + 1, number, result)


def solution(number):
    result = [0 for _ in range(3)]
    comb(0, 0, number, result)
    return answer