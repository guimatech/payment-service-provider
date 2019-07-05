const Sequelize = require('sequelize')
const sequelize = require('../database/database')
const dateUtil = require('../util/date')
const mathUtil = require('../util/math')
const Transaction = require('./transaction').Transaction
const isPaymentOnDebit = require('./transaction').isPaymentOnDebit

const eStatusPayable = {
  PAID: 'paid',
  WAITING_FUNDS: 'waiting_funds'
}

const ePercentFee = {
  DEBIT_CARD: 3,
  CREDIT_CARD: 5
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
  if (isPaymentOnDebit(transaction.paymentMethod)) {
    return {
      value: discountProcessingRate(transaction.value, ePercentFee.DEBIT_CARD),
      status: eStatusPayable.PAID,
      paymentDate: dateUtil.formatDateToPostgres(new Date()),
      transactionId: transaction.id
    }
  } else {
    return {
      value: discountProcessingRate(transaction.value, ePercentFee.CREDIT_CARD),
      status: eStatusPayable.WAITING_FUNDS,
      paymentDate: dateUtil.formatDateToPostgres(dateUtil.addDays(new Date(), 30)),
      transactionId: transaction.id
    }
  }
}

const discountProcessingRate = (value, fee) => {
  return value - mathUtil.getValueDiscount(value, fee)
}

module.exports = { 
  Payable, 
  eStatusPayable,
  getPayableFromTransaction
}
