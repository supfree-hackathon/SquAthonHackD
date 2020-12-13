const express = require('express');
const router = express.Router();

const Store = require('../models/store');

router.get('/allStores', (req, res, next) => {
    Store.find()
    .exec()
    .then(elems => {
        console.log(elems);
        res.status(200).json(elems);
    })
    .catch(err => {
        console.log(err);
        res.status(500).json({
            error: err
        });
    });
});

router.post('/addStore', (req, res, next) => {
    Store.find({ latitude: req.body.latitude, longitude: req.body.longitude })
    .exec()
    .then(store => {
        if(store.length >= 1) {
            return res.status(409).json({
                message: "Store exists"
            });
        } else {
            const store = new Store ({
                name: req.body.name,
                latitude: req.body.latitude, 
                longitude: req.body.longitude
            });
            store.save(result => {
                console.log(result);
                res.status(201).json({
                    message: 'Store Created'
                });
            })
            .catch(err => {
                res.status(500).json({
                    error: err
                });
            })
        }
    })
    .catch(err => {
        res.status(500).json({
            error: err
        });
    })
});

module.exports = router;