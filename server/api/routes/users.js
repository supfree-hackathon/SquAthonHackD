const express = require('express');
//const { Mongoose } = require('mongoose');
const router = express.Router();
const bcrypt = require('bcrypt');

const User = require('../models/user');

router.get('/login/:username/:password', (req, res, next) => {
    User.find({username: req.params.username})
        .exec()
        .then(user => {
            if(user.length < 1) {
                return res.status(401).json({
                    message: "Auth failed",
                    jh: user
                }); 
            } else {
                bcrypt.compare(req.params.password, user[0].password, (err, result) => {
                    if(err) {
                        return res.status(401).json({
                            message: "Auth failed"
                        });
                    }
                    if (result) {
                        return res.status(200).json({
                            message: "login succesful"
                        });
                    }
                    return res.status(401).json({
                        message: "Auth failed"
                    });
                })
            }
        })
        .catch(error => {
            res.status(500).json({
                error: error
            });
        });
});
function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}
router.post('/signup', (req, res, next) => {
    User.find({username: req.body.username})
        .exec()
        .then(user => {
            if (user.length >= 1) {
                return res.status(409).json({
                    message: "User exists"
                });
            } else {
                bcrypt.hash(req.body.password, 10, (err, hash) => {
                    if (err) {
                        return res.status(500).json({
                            error: err
                        });
                    }  else if (!validateEmail(req.body.email)){
                        return res.status(404).json({
                            message: "Wrong email"
                        });
                    } else {
                        const user = new User ({
                            username: req.body.username,
                            password: hash,
                            email: req.body.email,
                            games: 2,
                            points: 0,
                            used_points: 0
                        });
                        user.save()
                            .then(result => {
                                console.log(result);
                                res.status(201).json({
                                    message: 'User created'
                                })
                            })
                            .catch( err => {
                                res.status(500).json({
                                    error: err
                                });
                            })      
                    }
                });
            }
        })
        .catch();
});

/*router.delete('/:username', (req, res, next) => {
    User.findOne({username: req.params.id}, function(err) {
        if (err){
            res.status(500).json({
                error: err
            });
        } else {
            res.status(200).json({
                message: 'Deleted'
            });
        }
    });
});*/

router.post('/games', (req, res, next) => {
    User.findOne({username: req.body.username}, function(err, user) {
        if (err){
            res.status(500).json({
                error: err
            });
        } else {
            if(!user) {
                res.status(404).json({
                    error: "no user"
                });
            } else {
                if (user.games == 0){
                    res.status(500).json({
                        error: "No more games"
                    });
                }
                else {
                    user.games = user.games-1;
                    user.save(function(error, updated) {
                        if(error) {
                            res.status(500).json({
                                error: err
                            });
                        } else {
                            res.status(200).json({
                                message: "User updated",
                                user: updated
                            });
                        }
                    })
                }
            }
        }
    });
});

router.post('/transaction', (req, res, next) => {
    User.findOne({username: req.body.username}, function(err, user) {
        if (err){
            res.status(500).json({
                error: err
            });
        } else {
            if(!user) {
                res.status(404).json({
                    error: "No user"
                });
            } else {
                if (user.points < req.body.points) {
                    res.status(404).json({
                        error: "Cannot convert points"
                    });
                } else {
                    user.points = user.points - req.body.points;
                    user.used_points = user.used_points + req.body.points;
                    user.save(function(error, updated) {
                        if(error) {
                            res.status(500).json({
                                error: err
                            });
                        } else {
                            res.status(200).json({
                                message: "Transaction made",
                                user: updated
                            });
                        }
                    })
                }
                    
            }
        }
    });
});


router.post('/pointsUpdate', (req, res, next) => {
    console.log(req.body);
    User.findOne({username: req.body.username}, function(err, user) {
        if (err){
            res.status(500).json({
                error: err
            });
        } else {
            if(!user) {
                res.status(404).json({
                    error: "no user"
                });
            } else {
                user.points = user.points + req.body.points;
                user.save(function(error, updated) {
                    if(error) {
                        res.status(500).json({
                            error: err
                        });
                    } else {
                        res.status(200).json({
                            message: "User updated",
                            user: updated
                        });
                    }
                })
            }
        }
    });   
});

module.exports = router;