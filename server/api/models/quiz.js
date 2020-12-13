const mongoose = require('mongoose');
const Schema = mongoose.Schema;
mongoose.Promise = global.Promise;

const quizSchema = new Schema({
    quizname: {
        type: String,
        required: true,
        unique: true
    },
    correct: {
        type: String,
        required: true
    },
    false1: {
        type: String,
        required: true
    },
    false2: {
        type: String,
        required: true
    },
    false3: {
        type: String,
        required: true
    },

});
//'Quiz' => quizes
module.exports = mongoose.model('Quiz', quizSchema);