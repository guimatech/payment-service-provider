const Transaction = require('../model/transaction').Transaction
const handleSensitiveInformation = require('../model/transaction').handleSensitiveInformation
const statusHttp = require('http-status')
const createPayableFromTransaction = require('../controller/payable').createPayableFromTransaction
const sequelize = require('../database/database')

exports.findByPk = (request, response, next) => {
  const id = request.params.id

  Transaction.findByPk(id)
    .then(transaction => {
      if (transaction) {
        response.status(statusHttp.OK).send(transaction)
      } else {
        response.status(statusHttp.NOT_FOUND).send()
      }
    })
    .catch(error => next(error))
}

exports.findAll = (request, response, next) => {
  let limite = parseInt(request.query.limite || 0)
  let page = parseInt(request.query.page || 0)

  if (!Number.isInteger(limite) || !Number.isInteger(page)) {
    response.status(statusHttp.BAD_REQUEST).send()
  }

  const ITEMS_PER_PAGE = 10

  limite = limite > ITEMS_PER_PAGE || limite <= 0 ? ITEMS_PER_PAGE : limite
  page = page <= 0 ? 0 : page * limite

  Transaction.findAll({ limit: limite, offset: page })
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
    response.status(statusHttp.CREATED).send()
  })
  .catch(error => next(error))
}
