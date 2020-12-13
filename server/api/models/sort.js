const mongoose = require('mongoose');
const Schema = mongoose.Schema;
mongoose.Promise = global.Promise;

const sortSchema = new Schema({
    sortname: {
        type: String,
        required: true,
        unique: true
    },
    sorting: {
        type: String,
        required: true
    }
    

});
//'Sort' => sortgames
module.exports = mongoose.model('Sort', sortSchema);