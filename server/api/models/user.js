const mongoose = require('mongoose');
const Schema = mongoose.Schema;
mongoose.Promise = global.Promise;

const userSchema = new Schema({
    username: {
        type: String,
        required: true,
        unique: true 
        //ref: εδω βαλε το username που θες να κανουμε ref
    },
    password: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    points: {
        type: Number
    },
    games: {
        type: Number
    },
    used_points: {
        type: Number
    } 
});
//'User' => users
module.exports = mongoose.model('User', userSchema);