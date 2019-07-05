const Sequelize = require('sequelize')
const sequelize = require('../database/database')

const ePaymentMethod = {
  DEBIT_CARD: 'debit_card',
  CREDIT_CARD: 'credit_card'
}

const Transaction = sequelize.define('transaction', {
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
  description: {
    allowNull: false,
    type: Sequelize.STRING(255),
    validate: {
      len: [2, 255]
    }
  },
  paymentMethod: {
    allowNull: false,
    type: Sequelize.ENUM(
      ePaymentMethod.DEBIT_CARD, 
      ePaymentMethod.CREDIT_CARD
    )
  },
  cardNumber: {
    allowNull: false,
    type: Sequelize.STRING(4),
    validate: {
      len: [4, 4]
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
      len: [3, 3]
    }
  }
})

const handleSensitiveInformation = (cardNumber) => {
  return cardNumber.substr(-4)
}

const isPaymentOnDebit = (paymentMethod) => {
  return (paymentMethod == ePaymentMethod.DEBIT_CARD)
}

const isPaymentOnCredit = (paymentMethod) => {
  return (paymentMethod == ePaymentMethod.CREDIT_CARD)
}

module.exports =  {
  Transaction,
  handleSensitiveInformation,
  isPaymentOnDebit,
  isPaymentOnCredit
}
