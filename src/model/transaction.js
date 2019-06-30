const Sequelize = require('sequelize')
const sequelize = require('../database/database')

const Transaction = sequelize.define('transaction', {
  id: {
    allowNull: false,
    autoIncrement: true,
    primaryKey: true,
    type: Sequelize.BIGINT
  },
  value: {
    allowNull: false,
    type: Sequelize.DECIMAL(10, 2)
  },
  description: {
    allowNull: false,
    type: Sequelize.STRING(255),
    validate: {
      len: [2, 255]
    }
  },
  paymentMethod: {
    allowNull: false,
    type: Sequelize.ENUM('debit_card', 'credit_card')
  },
  cardNumber: {
    allowNull: false,
    type: Sequelize.STRING(19),
    validate: {
      len: [14, 19]
    }
  },
  cardHolderName: {
    allowNull: false,
    type: Sequelize.STRING(255),
    validate: {
      len: [2, 255]
    }
  },
  expirationDate: {
    allowNull: false,
    type: Sequelize.DATE
  },
  cvv: {
    allowNull: false,
    type: Sequelize.STRING(3),
    validate: {
      len: [3]
    }
  }
})

module.exports = Transaction
