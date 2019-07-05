const express = require('express');
const controller = require('../controller/transaction');

const router = express.Router();

router.get('/transactions/:id', controller.findByPk);

router.get('/transactions', controller.findAll);

router.post('/transactions', controller.create);

module.exports = router;
