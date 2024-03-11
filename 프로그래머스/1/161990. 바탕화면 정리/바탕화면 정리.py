def solution(wallpaper):
    lux = len(wallpaper)
    luy = len(wallpaper[0])
    rdx = rdy = 0
    for i in range(len(wallpaper)):
        for j in range(len(wallpaper[i])):

            if wallpaper[i][j] == "#":
                current_start = [i, j]
                current_end = [i + 1, j + 1]
                if current_start[0] < lux: lux = current_start[0]
                if current_start[1] < luy: luy = current_start[1]
                if current_end[0] > rdx: rdx = current_end[0]
                if current_end[1] > rdy: rdy = current_end[1]
    answer = [lux, luy, rdx, rdy]
    return answer