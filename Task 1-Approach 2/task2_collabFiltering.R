# Task 2: User - Business Collaboration Filtering.

#install.packages("recommenderlab")
library("recommenderlab")
library("reshape2")

data.businessid = read.csv("E:\\data\\userBid.csv",header = 0)

colnames(data.businessid) = c("Userid" ,"businessID","rating")

# Users and Restaurant data from North Carolina.
head(data.businessid)
summary(data.businessid)
dim(data.businessid)

#Create ratings matrix. Rows = userID, Columns = businessID
data.sparse = sparseMatrix(as.integer(data.businessid$Userid), as.integer(data.businessid$businessID), x = data.businessid$rating)
dim(data.sparse)

# Attaching colnames and rownames
colnames(data.sparse) = levels(data.businessid$businessID)
rownames(data.sparse) = levels(data.businessid$Userid)

dim(data.sparse)

rating.matrix.sparse <- as(data.sparse, "realRatingMatrix")
rating.matrix.normalized <- normalize(rating.matrix.sparse)


#Create Recommender Model. "UBCF" stands for User-Based Collaborative Filtering.
recommender_model <- Recommender(rating.matrix.normalized, method = "UBCF", param=list(method="Cosine",nn=30))

#Top 10 recommendations for 1st user in dataset
recom <- predict(recommender_model, rating.matrix.sparse[201], n=10) 
recom_list <- as(recom, "list") #convert recommenderlab object to readable list

#Obtain recommendations
recom_result <- matrix(0,10)
for (i in c(1:10)){
  recom_result[i] <- movies[as.integer(recom_list[[1]][i]),2]
}


#split train test
# 0.8 0.2
# 0.9 0.1 

evaluation_scheme <- evaluationScheme(rating.matrix.sparse,method="split", train=0.9, given=1, goodRating = 2) 
evaluation_results <- evaluate(evaluation_scheme, method="UBCF", n=c(1,40001,5,10,15,20,40,50,60,100,150,250,300,350,400,450,500,550,600,700,750,800,900,901,650,680))
eval_results <- getConfusionMatrix(evaluation_results)[[1]]   


eval_results <- data.frame(eval_results)
eval_results <- eval_results[order(eval_results$recall),] 

eval_results <-  eval_results[1:ncol(eval_results)-1,]

# Evaluation Plot
plot(eval_results$FPR,eval_results$TPR,main="ROC Curve - UBCF", xlab="FPR", ylab="TPR")
lines(eval_results$FPR,eval_results$TPR)

write.csv(eval_results, file = "E:\\data\\evalResults.csv")

# Loading Model 1 Results.


names(eval_results)
