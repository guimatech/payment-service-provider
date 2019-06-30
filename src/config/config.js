module.exports = {
  development: {
    database: {
      host: 'localhost',
      port: 5432,
      name: 'postgres',
      dialect: 'postgres',
      user: 'postgres',
      password: 'mypassword'
    }
  },
  production: {
    database: {
      host: process.env.DB_HOST,
      port: process.env.DB_PORT
    }
  }
}
