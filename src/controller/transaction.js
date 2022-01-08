const service = require('../service/transaction')
const httpStatus = require('http-status')

exports.findByPk = (request, response, next) => {
    try {
        const transaction = service.findByPk(request.params.id)
        if (!transaction) {
            response.status(httpStatus.NOT_FOUND).send()
            return
        }

        response.status(httpStatus.OK).send(transaction)
    } catch (error) {
        next(error)
    }
}

exports.findAll = (request, response, next) => {
    try {
        const transactions = service.findAll(request.query.limit, request.query.page)
        response.send(transactions)
    } catch (error) {
        next(error)
    }
}

exports.create = (request, response, next) => {
    try {
        const transaction = service.create(request.body)
        response.status(httpStatus.CREATED).send(transaction)
    } catch (error) {
        next(error)
    }
}