def solution(k, score):
    answer = []
    ranking = []

    for i in score:
        if len(ranking) < k:  # len(ranking) = 0, 1, 2
            ranking.append(i)
            ranking.sort(reverse=True)
            answer.append(ranking[-1])
        else:  # len(ranking) = 3
            for idx, value in enumerate(ranking):
                if i >= value:
                    ranking.insert(idx, i)
                    ranking = ranking[:k]
                    break

            answer.append(ranking[-1])

    return answer