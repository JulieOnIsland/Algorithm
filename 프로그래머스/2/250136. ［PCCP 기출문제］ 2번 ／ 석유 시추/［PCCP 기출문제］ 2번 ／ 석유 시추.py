# flood fill

def solution(land):
    answer = 0

    global init
    init = 100
    flood_mark = dict()  # [101, 8], [102, 7], etc

    visited = [[0] * len(land[0]) for _ in range(len(land))]

    # main
    for i in range(len(land)):  # 세로
        for j in range(len(land[0])):  # 가로
            if land[i][j] == 1 and visited[i][j] == 0: # 석유가 있으면서 방문한 적이 없을 때 
                count = bfs(land, visited, i, j)
                flood_mark[init] = count


    for i in range(len(land[0])):
        temp = 0
        type = set()
        for j in range(len(land)):
            if land[j][i] != 0:
                type.add(land[j][i])

        for k in type:
            temp += flood_mark[k]
        if temp > answer: answer = temp

    return answer



def bfs(land, visited, i, j): # s: 시작점, v
    queue = [[i, j]] # 큐 생성, 시작점 큐에 넣기
    visited[i][j] = 1 # 시작점 방문 표시

    # init 변수 1 늘리기
    global init
    init += 1
    land[i][j] = init

    dr = [-1, 0, 1, 0]
    dc = [0, 1, 0, -1]

    # 몇 개인지 세기
    count = 1

    while queue: # 큐에 정점이 남아 있다면
        cn = queue.pop(0) # current node
        cr = cn[0] # current row
        cc = cn[1] # current col
        for dir in range(len(dr)): # four directions
            nr = cr+dr[dir] # next row
            nc = cc+dc[dir] # next col
            if (0 <= nr < len(land)) and (0 <= nc < len(land[0])) and (visited[nr][nc] == 0) and land[nr][nc] == 1: # 범위 안에 들어가면서
                # if (visited[nr][nc] == 0) and land[nr][nc] == 1:  # 방문한 적이 없고, 1이라면
                queue.append([nr, nc]) # 큐에 넣어주기
                land[nr][nc] = init # 바꿔주기
                visited[nr][nc] = 1 # 방문처리
                count += 1 # 개수 증가

    return count