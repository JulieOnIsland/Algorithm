def solution(numbers):
    answer = 0
    original = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    mark = [0 for _ in range(len(original))]
    
    for num in numbers:
        mark[num] = 1

    # mark 배열에서 numbers에 들어 있으면 1, 없으면 0으로 표시됨
    # 등장하지 않은 수, 즉 mark에서 0인 숫자만 더하면 됨

    for idx, value in enumerate(mark):
        if value == 0:
            answer += idx
            
    return answer