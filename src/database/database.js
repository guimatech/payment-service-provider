const Sequelize = require('sequelize')

const environment = process.env.NODE_ENV || 'development'

const config = require('../config/config.js')[environment]

const sequelize = new Sequelize(
  config.database.name,
  config.database.user,
  config.database.password,
  {
    host: config.database.host,
    dialect: config.database.dialect,
    pool: {
      max: 5,
      min: 0,
      acquire: 30000,
      idle: 10000
    }
  }
)

module.exports = sequelize