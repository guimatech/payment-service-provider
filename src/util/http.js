const httpStatus = require('http-status')

function HttpException(message) {
  this.message = message
  this.name = "httpException"
}

const treatPageAndLimit = (limite, page) => {
  if (!Number.isInteger(limite) || !Number.isInteger(page)) {
    throw new HttpException()
  }
  
  const ITEMS_PER_PAGE = 10
  
  limite = limite > ITEMS_PER_PAGE || limite <= 0 ? ITEMS_PER_PAGE : limite
  page = page <= 0 ? 0 : page * limite
    
  return { limit: limite, offset: page }
}

module.exports = {
  treatPageAndLimit
}