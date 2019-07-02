const express = require('express');
const controller = require('../controller/transaction');

const router = express.Router();

router.get('/transactions/:id', controller.findById);

router.get('/transactions', controller.findAll);

router.post('/transactions', controller.create);

router.put('/transactions/:id', controller.update);

router.delete('/transactions/:id', controller.delete);

module.exports = router;
