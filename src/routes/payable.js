const express = require('express');
const controller = require('../controller/payable');

const router = express.Router();

router.get('/payables/:id', controller.findByPk);

router.get('/payables', controller.findAll);

module.exports = router;
