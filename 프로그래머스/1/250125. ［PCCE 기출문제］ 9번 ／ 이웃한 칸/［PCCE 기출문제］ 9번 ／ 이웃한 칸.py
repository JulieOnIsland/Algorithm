def solution(board, h, w):
    answer = 0
    dh = [-1, 0, 1, 0]  # dir of h
    dw = [0, 1, 0, -1]  # dir of w

    for i in range(len(dh)):  # check four directions
        color = board[h][w]
        nh = h + dh[i]  # next h
        nw = w + dw[i]  # next w
        if 0 <= nh < len(board) and 0 <= nw < len(board) and board[nh][nw] == color:
            # 범위에 들어가면서 같은 색깔이라면 answer++
            answer += 1

    return answer