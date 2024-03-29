def comb(cnt, start, numbers, result):
    if cnt == 2:
        # print(result)
        return answer.add(result[0]+result[1])

    for i in range(start, len(numbers)):
        result[cnt] = numbers[i]
        comb(cnt+1, i+1, numbers, result)

def solution(numbers):
    global answer
    answer = set()
    result = [0 for _ in range(2)]

    comb(0, 0, numbers, result)
    answer = sorted(list(answer))

    return answer