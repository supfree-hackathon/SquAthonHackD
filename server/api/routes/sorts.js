const express = require('express');
const router = express.Router();

const Sort = require('../models/sort');

router.get('/allSorts', (req, res, next) => {
    Sort.find()
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

router.post('/addSort', (req, res, next) => {
    Sort.find({ name: req.body.name })
    .exec()
    .then(sort => {
        if(sort.length >= 1) {
            return res.status(409).json({
                message: "Quiz exists"
            });
        } else {
            const sort = new Sort ({
                name: req.body.name,
                sorting: req.body.sorting
            });
            sort.save(result => {
                console.log(result);
                res.status(201).json({
                    message: 'Quiz Created'
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