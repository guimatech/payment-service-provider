const Transaction = require('../model/transaction')
const status = require('http-status')

exports.findById = (request, response, next) => {
  const id = request.params.id

  Transaction.findById(id)
    .then(transaction => {
      if (transaction) {
        transaction.cardNumber = transaction.cardNumber.substr(-4) 
        response.status(status.OK).send(transaction)
      } else {
        response.status(status.NOT_FOUND).send()
      }
    })
    .catch(error => next(error))
}

exports.findAll = (request, response, next) => {
  let limite = parseInt(request.query.limite || 0)
  let page = parseInt(request.query.page || 0)

  if (!Number.isInteger(limite) || !Number.isInteger(page)) {
    response.status(status.BAD_REQUEST).send()
  }

  const ITEMS_PER_PAGE = 10

  limite = limite > ITEMS_PER_PAGE || limite <= 0 ? ITEMS_PER_PAGE : limite
  page = page <= 0 ? 0 : page * limite

  Transaction.findAll({ limit: limite, offset: page })
    .then(transactions => {
      transactions.forEach(
        function(transaction) { 
          transaction.cardNumber = transaction.cardNumber.substr(-4) 
        })
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

  Transaction.create({
    value: value,
    description: description,
    paymentMethod: paymentMethod,
    cardNumber: cardNumber,
    cardHolderName: cardHolderName,
    expirationDate: expirationDate,
    cvv: cvv
  })
    .then(() => {
      response.status(status.CREATED).send()
    })
    .catch(error => next(error))
}

exports.update = (request, response, next) => {
  const id = request.params.id

  const value = request.body.value
  const description = request.body.description
  const paymentMethod = request.body.paymentMethod
  const cardNumber = request.body.cardNumber
  const cardHolderName = request.body.cardHolderName
  const expirationDate = request.body.expirationDate
  const cvv = request.body.cvv

  Transaction.findById(id)
    .then(transaction => {
      if (transaction) {
        Transaction.update(
          {
            value: value,
            description: description,
            paymentMethod: paymentMethod,
            cardNumber: cardNumber,
            cardHolderName: cardHolderName,
            expirationDate: expirationDate,
            cvv: cvv
          },
          { where: { id: id } }
        )
          .then(() => {
            response.status(status.OK).send()
          })
          .catch(error => next(error))
      } else {
        response.status(status.NOT_FOUND).send()
      }
    })
    .catch(error => next(error))
}

exports.delete = (request, response, next) => {
  const id = request.params.id

  Transaction.findById(id)
    .then(transaction => {
      if (transaction) {
        Transaction.destroy({
          where: { id: id }
        })
          .then(() => {
            response.status(status.OK).send()
          })
          .catch(error => next(error))
      } else {
        response.status(status.NOT_FOUND).send()
      }
    })
    .catch(error => next(error))
}
