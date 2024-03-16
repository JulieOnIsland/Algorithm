def solution(n, m, section):
    answer = 0
    is_colored = [0 for _ in range(len(section))]

    for i in range(len(section)):
        val = section[i]  # val = 2, 3, 6
        if is_colored[i] == 0:
            is_colored_index = 0
            for j in range(m):
                if i + is_colored_index < len(is_colored) and val + j == section[i + is_colored_index]:
                    is_colored[i + is_colored_index] = 1  # 색칠됐다고 표시
                    is_colored_index += 1
            answer += 1

    return answer