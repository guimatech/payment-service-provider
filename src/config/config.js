module.exports = {
  development: {
    database: {
      host: 'db',
      port: 5432,
      name: 'psp',
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
