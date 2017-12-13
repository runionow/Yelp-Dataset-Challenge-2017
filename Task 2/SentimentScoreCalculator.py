import pandas as pd
from afinn import Afinn

# Cleaning the data and finding the sentiment score for each review text in the dataset.

df= pd.read_csv("reviewsAndTips .csv",index_col=0)
length=df.__len__()

print(length)

df=df.dropna()

afinn = Afinn()
pscore = []

for text in df['Text']:
    pscore.append(afinn.score(text))

df['pscore'] = pscore

df.to_csv('sentiment_analysis.csv')

