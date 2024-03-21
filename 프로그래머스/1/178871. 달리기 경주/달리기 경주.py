def solution(players, callings):
    race = dict()

    for idx, player in enumerate(players):
        race[player] = idx

    for player in callings:
        idx = race.get(player) # 앞지른 선수의 랭킹
        lost_player_name = players[idx-1] # 뒤처진 선수의 이름
        
        race[player] = idx - 1
        race[lost_player_name] = idx
        
        # players에 반영 
        players[idx-1] = player
        players[idx] = lost_player_name
        
    return players