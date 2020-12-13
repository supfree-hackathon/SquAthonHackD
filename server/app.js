const express = require("express");
const app = express();
const morgan = require("morgan");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");

const userRoutes = require("./api/routes/users");
const storeRoutes = require("./api/routes/stores");
const quizRoutes = require("./api/routes/quizes");
const sortRoutes = require("./api/routes/sorts");

const uri1 = "mongodb://ivanakos:" + process.env.MONGO_ATLAS_PW + "@cluster0-shard-00-00.xxvww.mongodb.net:27017,cluster0-shard-00-01.xxvww.mongodb.net:27017,cluster0-shard-00-02.xxvww.mongodb.net:27017/replace?ssl=true&replicaSet=atlas-92jxad-shard-0&authSource=admin&retryWrites=true&w=majority"
mongoose.connect(uri1,{
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useCreateIndex: true
});


app.use(morgan("dev"));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept, Authorization"
  );
  if (req.method === "OPTIONS") {
    res.header("Access-Control-Allow-Methods", "PUT, POST, PATCH, DELETE, GET");
    return res.status(200).json({});
  }
  next();
});

// Routes which should handle requests
app.use("/users", userRoutes);
app.use("/stores", storeRoutes);
app.use("/sorts", sortRoutes);
app.use("/quizes", quizRoutes);

app.use((req, res, next) => {
  const error = new Error("Not found");
  error.status = 404;
  next(error);
});

app.use((error, req, res, next) => {
  res.status(error.status || 500);
  res.json({
    error: {
      message: error.message
    }
  });
});

module.exports = app;
