const express = require('express');
const controller = require('../controller/payable');

const router = express.Router();

router.get('/payables/:id', controller.findByPk);

router.get('/payables', controller.findAll);

router.get('/payablesBalance', controller.findBalance);

module.exports = router;
