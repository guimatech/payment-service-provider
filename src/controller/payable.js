const Payable = require('../service/payable').Payable
const httpStatus = require('http-status')
const httpUtil = require('../util/http.js')

exports.findByPk = (request, response, next) => {
  try {
    const payable = Payable.findByPk(request.params.id)
    if (!payable) {
      response.status(httpStatus.NOT_FOUND).send()
      return
    }

    response.status(httpStatus.OK).send(payable)
  } catch(error) {
    next(error)
  }
}

exports.findAll = (request, response, next) => {
  try {
    const payables = Payable.findAll(request.query.limit, request.query.page)
    response.send(payables)
  } catch(error) {
    response.status(httpStatus.BAD_REQUEST).send()
  }
}

exports.findBalance = (request, response, next) => Payable.findAll(request, response, next)
