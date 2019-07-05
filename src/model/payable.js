const Sequelize = require('sequelize')
const sequelize = require('../database/database')
const dateUtil = require('../util/date')
const Transaction = require('./transaction').Transaction
const isPaymentOnCredit = require('./transaction').isPaymentOnCredit

const eStatusPayable = {
  PAID: 'paid',
  WAITING_FUNDS: 'waiting_funds'
}

const Payable = sequelize.define('payable', {
  id: {
    allowNull: false,
    autoIncrement: true,
    primaryKey: true,
    type: Sequelize.INTEGER
  },
  value: {
    allowNull: false,
    type: Sequelize.DECIMAL(10, 2)
  },
  status: {
    allowNull: false,
    type: Sequelize.ENUM(
      eStatusPayable.PAID, 
      eStatusPayable.WAITING_FUNDS
    )
  },
  paymentDate: {
    allowNull: false,
    type: Sequelize.DATE
  }
})

Payable.belongsTo(Transaction)

const getPayableFromTransaction = (transaction) => {
  let value = transaction.value
  let status = eStatusPayable.PAID
  let paymentDate = dateUtil.formatDateToPostgres(new Date())

  if (isPaymentOnCredit(transaction.paymentMethod)) {
    value = transaction.value
    status = eStatusPayable.WAITING_FUNDS
    paymentDate = dateUtil.formatDateToPostgres(
                    dateUtil.addDays(new Date(), 30)
                  )
  }

  return {
    value: value,
    status: status,
    paymentDate: paymentDate,
    transactionId: transaction.id
  }
}

module.exports = { 
  Payable, 
  eStatusPayable,
  getPayableFromTransaction
}
