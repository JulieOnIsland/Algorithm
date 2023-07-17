
'''
swea_1954
'''

# -- direction -----------
dr = 0
# Right, Down, Left, Up
d_i = [0, 1, 0, -1]
d_j = [1, 0, -1, 0]
# ------------------------

N = 4
grid = [[0]*N for _ in range(N)]
i, j, cnt= 0, 0, 1
grid[i][j] = cnt
cnt += 1

while (cnt <= N**2):
    ni, nj = i+d_i[dr], j+d_j[dr]

    if 0<=ni<N and 0<=nj<N and grid[ni][nj] ==0:
        i, j = ni, nj
        grid[i][j] = cnt
        cnt += 1
    else:
        dr = (dr+1)%4

print(grid)












