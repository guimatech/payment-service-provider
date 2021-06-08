const Transaction = require('../model/transaction').Transaction
const handleSensitiveInformation = require('../model/transaction').handleSensitiveInformation
const httpStatus = require('http-status')
const createPayableFromTransaction = require('../controller/payable').createPayableFromTransaction
const httpUtil = require('../util/http.js')
const sequelize = require('../database/database')

exports.findByPk = (request, response, next) => {
  const id = request.params.id

  Transaction.findByPk(id)
    .then(transaction => {
      if (transaction) {
        response.status(httpStatus.OK).send(transaction)
      } else {
        response.status(httpStatus.NOT_FOUND).send()
      }
    })
    .catch(error => next(error))
}

exports.findAll = (request, response, next) => {
  Transaction.findAll(httpUtil.treatPageAndLimit(request, response))
    .then(transactions => {
      response.send(transactions)
    })
    .catch(error => next(error))
}

exports.create = (request, response, next) => {
  const value = request.body.value
  const description = request.body.description
  const paymentMethod = request.body.paymentMethod
  const cardNumber = request.body.cardNumber
  const cardHolderName = request.body.cardHolderName
  const expirationDate = request.body.expirationDate
  const cvv = request.body.cvv
  
  sequelize.transaction(() => {
    Transaction.create({
      value: value,
      description: description,
      paymentMethod: paymentMethod,
      cardNumber: handleSensitiveInformation(cardNumber),
      cardHolderName: cardHolderName,
      expirationDate: expirationDate,
      cvv: cvv
    }).then((transaction) => {
      createPayableFromTransaction(transaction)
    })
  })
  .then(() => {
    response.status(httpStatus.CREATED).send()
  })
  .catch(error => next(error))
}
