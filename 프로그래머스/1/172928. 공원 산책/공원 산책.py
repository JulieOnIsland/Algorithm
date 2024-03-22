op_dict = dict()  # E, W, N, S
op_dict["E"] = 0
op_dict["W"] = 1
op_dict["N"] = 2
op_dict["S"] = 3
dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]


def isInRange(park, cr, cc, op, n):
    r = len(park[0])
    c = len(park)

    dir_idx = op_dict[op]
    if 0 <= cr + dr[dir_idx] * n < r and 0 <= cc + dc[dir_idx] * n < c:  # 범위에 들어온다면
        return True
    return False


def isSafe(park, cr, cc, op, n):
    dir_idx = op_dict[op]
    for _ in range(n):
        nr = cr + dr[dir_idx]
        nc = cc + dc[dir_idx]
        if park[nc][nr] == "X":
            return False
        cr = nr
        cc = nc
    return True


def solution(park, routes):



    # 시작 좌표 찾기
    sr = sc = 0
    for i in range(len(park)):  # 세로번 만큼 반복
        for j in range(len(park[i])):  # 가로번 만큼 반복
            if park[i][j] == "S":
                # print(i, j) # col, row
                sr = j
                sc = i

    for order in routes:
        order_list = order.split()
        op = order_list[0]
        n = int(order_list[1])

        if isInRange(park, sr, sc, op, n) and isSafe(park, sr, sc, op, n):
            idx = op_dict[op]
            sr += dr[idx] * n
            sc += dc[idx] * n

    return [sc, sr]