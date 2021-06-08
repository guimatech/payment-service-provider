const Payable = require('../model/payable').Payable
const httpStatus = require('http-status')
const httpUtil = require('../util/http.js')
const sequelize = require('../database/database')
const eStatusPayable = require('../model/payable').eStatusPayable
const getPayableFromTransaction = require('../model/payable').getPayableFromTransaction

function PayableServiceException(message) {
  this.message = message;
  this.name = "PayableException";
}

exports.findByPk = id => {
  Payable.findByPk(id)
    .then(payable => payable)
    .catch(error => {
      throw new PayableServiceException("Error searching Payment.")
    })
}

exports.findAll = (limit, page) => {
  Payable.findAll(httpUtil.treatPageAndLimit(limit, page))
    .then(payables => payables)
    .catch(error => {
      throw new PayableServiceException("Error looking up Payments.")
    })
}

exports.createPayableFromTransaction = transaction => {
  Payable.create(
    getPayableFromTransaction(transaction)
  )
  .catch(error => {
    throw new PayableServiceException("Error creating Payments.")
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
