const Transaction = require('../model/transaction').Transaction
const handleSensitiveInformation = require('../model/transaction').handleSensitiveInformation
const createPayableFromTransaction = require('../service/payable').createPayableFromTransaction
const httpUtil = require('../util/http.js')
const sequelize = require('../database/database')

function TransactionServiceException(message) {
    this.message = message;
    this.name = "PayableException";
}

exports.findByPk = id => {
    return Transaction.findByPk(id)
        .then(transaction => transaction)
        .catch(error => {
            throw new TransactionServiceException('Error searching Transaction.');
        })
}

exports.findAll = (limit, page) => {
    return Transaction.findAll(httpUtil.treatPageAndLimit(limit, page))
        .then(transactions => transactions)
        .catch(error => {
            throw new TransactionServiceException('Error looking up Transactions.');
        })
}

exports.create = transaction => {
    return sequelize.transaction(() => {
            return Transaction.create({
                value: transaction.value,
                description: transaction.description,
                paymentMethod: transaction.paymentMethod,
                cardNumber: handleSensitiveInformation(transaction.cardNumber),
                cardHolderName: transaction.cardHolderName,
                expirationDate: transaction.expirationDate,
                cvv: transaction.cvv
            }).then(t => createPayableFromTransaction(t))
        })
        .then(t => t)
        .catch(error => {
            throw new TransactionServiceException('Error create Transaction.');
        })
}