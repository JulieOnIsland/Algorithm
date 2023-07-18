'''
SWEA 1208.Flatten
'''
for idx in range(1, 11):
    n = int(input()) # 덤프 횟수
    cnt = 0
    heights = list(map(int, input().split())) # 100개의 높이가 있는 리스트
    max_height = max(heights)
    min_height = min(heights)

    while (cnt < n):
        if max_height - min_height <= 1:
            break
        max_index = heights.index(max_height)
        heights[max_index] -= 1
        min_index = heights.index(min_height)
        heights[min_index] += 1
        cnt += 1
        max_height = max(heights)
        min_height = min(heights)

    diff = max_height - min_height
    print(f'#{idx} {diff}')
