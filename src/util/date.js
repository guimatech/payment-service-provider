const formatDateToPostgres = (date) => {
  var dd = String(date.getDate()).padStart(2, '0')
  var mm = String(date.getMonth() + 1).padStart(2, '0')
  var yyyy = date.getFullYear()

  return yyyy + '-' + mm + '-' + dd
}

const addDays = (date, days) => {
  date.setDate(date.getDate() + days);
  return date;
}

module.exports = {
  formatDateToPostgres,
  addDays
}