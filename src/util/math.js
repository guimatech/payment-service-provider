const getValueDiscount = (total, percentage) => {
  return total * (percentage / 100)
}

module.exports = {
  getValueDiscount
}