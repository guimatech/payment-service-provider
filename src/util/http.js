const httpStatus = require('http-status')

function HttpException(message) {
  this.message = message
  this.name = "httpException"
}

const treatPageAndLimit = (limit, page) => {
  if (!Number.isInteger(limit) || !Number.isInteger(page)) {
    throw new HttpException()
  }
  
  const ITEMS_PER_PAGE = 10
  
  limit = limit > ITEMS_PER_PAGE || limit <= 0 ? ITEMS_PER_PAGE : limit
  page = page <= 0 ? 0 : page * limit
    
  return { limit: limit, offset: page }
}

module.exports = {
  treatPageAndLimit
}