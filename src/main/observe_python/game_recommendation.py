import pandas as pd
from sklearn.ensemble import RandomForestClassifier
import sys
import json

def encode_data(player_data, game_recommendations):
    df = pd.DataFrame(player_data)
    # 这里我们改变数据转换的方式
    encoded_tags_list = []
    for tags in df['favorite_tags']:
        encoded = pd.get_dummies(pd.Series(tags))
        encoded_tags_list.append(encoded)
    encoded_tags = pd.concat(encoded_tags_list).fillna(0).astype(int)

    y = [1 if recommendation in player["favorite_tags"] else 0 for player in player_data for recommendation in game_recommendations]
    return encoded_tags, y


game_recommendations = [
    "精彩动作冒险游戏",
    "策略王国建设游戏",
    "恐怖生存冒险",
    "射击尖端",
]


# 从标准输入中加载玩家数据，而不是命令行参数
player_data = json.loads(sys.stdin.read())

X, y = encode_data(player_data, game_recommendations)

model = RandomForestClassifier()
model.fit(X, y)

# 使用编码的用户数据进行预测
user_data = {"favorite_tags": ["冒险", "恐怖"]}
user_encoded = pd.get_dummies(pd.Series(user_data["favorite_tags"])).sum().reindex(columns=X.columns, fill_value=0)
recommendation_scores = model.predict([user_encoded])

recommendations = [game for game, score in zip(game_recommendations, recommendation_scores) if score == 1]

# 输出到控制台
print(f"为用户X推荐的游戏：{', '.join(recommendations)}")

# 保存推荐结果到JSON文件
json_path = "F:/java.2022.muchuan/observers_last/src/main/observe_java/src/main/resources/data.json"
with open(json_path, 'w') as json_file:
    json.dump({"game_recommendation": ', '.join(recommendations)}, json_file)