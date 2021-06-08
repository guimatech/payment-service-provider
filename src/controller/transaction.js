const Transaction = require('../service/transaction').Transaction
const handleSensitiveInformation = require('../model/transaction').handleSensitiveInformation
const httpStatus = require('http-status')
const createPayableFromTransaction = require('../service/payable').createPayableFromTransaction
const httpUtil = require('../util/http.js')
const sequelize = require('../database/database')

exports.findByPk = (request, response, next) => {
  try {
    const transaction = Transaction.findByPk(request.params.id)
    if (!transaction) {
      response.status(httpStatus.NOT_FOUND).send()
      return
    } 
    
    response.status(httpStatus.OK).send(transaction)
  } catch(error) {
    next(error)
  }
}

exports.findAll = (request, response, next) => {
  try {  
    const transactions = Transaction.findAll(request.query.limit, request.query.page)
    response.send(transactions)
  } catch(error) {
    next(error)
  }
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
      cardNumber: cardNumber,
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
