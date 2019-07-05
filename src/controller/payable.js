const Payable = require('../model/payable').Payable
const status = require('http-status')
const getPayableFromTransaction = require('../model/payable').getPayableFromTransaction

function PayableException(message) {
  this.message = message;
  this.name = "PayableException";
}

exports.findByPk = (request, response, next) => {
  const id = request.params.id

  Payable.findByPk(id)
    .then(payable => {
      if (payable) {
        response.status(status.OK).send(payable)
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

  Payable.findAll({ limit: limite, offset: page })
    .then(payables => {
      response.send(payables)
    })
    .catch(error => next(error))
}

exports.createPayableFromTransaction = (transaction) => {
  Payable.create(
    getPayableFromTransaction(transaction)
  )
  .catch((error) => {
    throw new PayableException(error);
  })
}