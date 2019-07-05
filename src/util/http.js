const httpStatus = require('http-status')

const treatPageAndLimit = (request, response) => {
  let limite = parseInt(request.query.limite || 0)
  let page = parseInt(request.query.page || 0)
  
  if (!Number.isInteger(limite) || !Number.isInteger(page)) {
    response.status(httpStatus.BAD_REQUEST).send()
  }
  
  const ITEMS_PER_PAGE = 10
  
  limite = limite > ITEMS_PER_PAGE || limite <= 0 ? ITEMS_PER_PAGE : limite
  page = page <= 0 ? 0 : page * limite
    
  return { limit: limite, offset: page }
}

module.exports = {
  treatPageAndLimit
}