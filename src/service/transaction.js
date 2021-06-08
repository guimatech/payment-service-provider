const Transaction = require('../model/transaction').Transaction
const handleSensitiveInformation = require('../model/transaction').handleSensitiveInformation
const httpStatus = require('http-status')
const createPayableFromTransaction = require('../controller/payable').createPayableFromTransaction
const httpUtil = require('../util/http.js')
const sequelize = require('../database/database')

function TransactionServiceException(message) {
  this.message = message;
  this.name = "PayableException";
}

exports.findByPk = id => {
  Transaction.findByPk(id)
    .then(transaction => transaction)
    .catch(error => {
      throw new TransactionServiceException('Error searching Transaction.');
    })
}

exports.findAll = (limit, page) => {
  Transaction.findAll(httpUtil.treatPageAndLimit(limit, page))
    .then(transactions => transactions)
    .catch(error => {
      throw new TransactionServiceException('Error looking up Transactions.');
    })
}

exports.create = transaction => {
  sequelize.transaction(() => {
    Transaction.create({
      value: transaction.value,
      description: transaction.description,
      paymentMethod: transaction.paymentMethod,
      cardNumber: handleSensitiveInformation(transaction.cardNumber),
      cardHolderName: transaction.cardHolderName,
      expirationDate: transaction.expirationDate,
      cvv: transaction.cvv
    }).then((transaction) => {
      createPayableFromTransaction(transaction)
    })
  })
  .then(() => transaction)
  .catch(error => {
    throw new TransactionServiceException('Error create Transaction.');
  })
}
