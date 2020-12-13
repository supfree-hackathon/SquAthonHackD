const mongoose = require('mongoose');
const Schema = mongoose.Schema;
mongoose.Promise = global.Promise;

const storeSchema = new Schema({
    storename: {
        type: String,
        required: true
    },
    latitude: {
        type: String,
        required: true
    },
    longtitude: {
        type: String,
        required: true
    }
});
//'Store' => stores
module.exports = mongoose.model('Store', storeSchema);