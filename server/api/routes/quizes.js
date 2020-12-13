const express = require('express');
const router = express.Router();

const Quiz = require('../models/quiz');

router.get('/allQuizes', (req, res, next) => {
    Quiz.find()
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

router.post('/addQuiz', (req, res, next) => {
    Quiz.find({ name: req.body.name })
    .exec()
    .then(quiz => {
        if(quiz.length >= 1) {
            return res.status(409).json({
                message: "Quiz exists"
            });
        } else {
            const quiz = new Quiz ({
                name: req.body.name,
                correct: req.body.correct, 
                false1: req.body.false1,
                false2: req.body.false2,
                false3: req.body.false3
            });
            quiz.save(result => {
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