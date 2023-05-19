const updateRate = (currencies) => {
    const currencySelect = document.getElementById("currency");
    const operationSelect = document.getElementById("operation");
    const rateInput = document.getElementById("rate");
    const currencyName = currencySelect.options[currencySelect.selectedIndex].value;
    const operation = operationSelect.options[operationSelect.selectedIndex].value;
    rateInput.value = getRate(currencies, currencyName, operation);
}

const getRate = (currencies, ccy, operation) => {
    for (let i = 0; i < currencies.length; i++) {
        if (currencies[i].ccy === ccy) {
            return currencies[i][operation];
        }
    }
}