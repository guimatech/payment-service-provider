const Payable = require('../model/payable').Payable
const httpStatus = require('http-status')
const httpUtil = require('../util/http.js')
const sequelize = require('../database/database')
const eStatusPayable = require('../model/payable').eStatusPayable
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
        response.status(httpStatus.OK).send(payable)
      } else {
        response.status(httpStatus.NOT_FOUND).send()
      }
    })
    .catch(error => next(error))
}

exports.findAll = (request, response, next) => {
  Payable.findAll(httpUtil.treatPageAndLimit(request, response))
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

exports.findBalance = (request, response, next) => {
  Payable.findAll({
    attributes: [[sequelize.fn('SUM', sequelize.col('value')), 'value']],
    where: {
      status: eStatusPayable.PAID
    }
  })
    .then(available => {
      Payable.findAll({
        attributes: [[sequelize.fn('SUM', sequelize.col('value')), 'value']],
        where: {
          status: eStatusPayable.WAITING_FUNDS
        }
      })
        .then(waiting_funds => {
          const balance = { 
            "available": available[0].value || 0,
            "waiting_funds": waiting_funds[0].value || 0
          }
          response.send(balance)
        })
        .catch(error => next(error))
    })
    .catch(error => next(error))
}
