

class group {
    constructor() {
        this.users = []
        this.transactions = []
    }

    addUser(user) {
        this.users.push(user)
    }

    addExpense(expense) {
        this.transactions.push(expense)
    }

    getUserBalance(user) {
        return user.getBalance()
    }
}

class user {
    constructor(name,email,mobile) {
        this.name = name
        this.email = email
        this.mobile = mobile
        this.balance = new Map()
    }
    getBalance() {
        return this.balance
    }
}

class expense {
    constructor(paidBy, expenseType, amount) {

        this.paidBy = paidBy
        this.expenseType = expenseType
        this.amount = amount
    }
    split() {
        const paidBy = this.paidBy
        const users = this.users
        const amount = this.amount
        const dividedAmount = amount/users.length
        for(let user of users) {
            let currentBalance = paidBy.balance.get(user)
            if(currentBalance===undefined)
                paidBy.balance.set(user,amount)
            else
                paidBy.balance.set(user,amount+currentBalance)

            let takerBalance = user.balance.get(paidBy)
            if(takerBalance===undefined)
                user.balance.set(paidBy,-amount)
            else
                user.balance.set(paidBy,takerBalance-amount)
        }
    }
}

class expensesWithPercentage extends expense {
    constructor(paidBy, expenseType, amount, user, percentage) {
        super(paidBy, expenseType, amount)
        this.payments = []
    }
    addUserWithPercentage(taker,percentage) {
        this.payments.push({user: taker, percentage: percentage})
    }

    split() {
        const paidBy = this.paidBy
        const transactions = this.transactions
        const amount = this.amount
        let percentage = 0
        for(let transaction of transactions) {
            percentage+=transaction.percentage
        }
        if(percentage!==100)
            throw "exception are not adding to 100"

        for(let transaction of transactions) {
            let currentBalance = paidBy.balance.get(transaction.user)
            if(currentBalance===undefined)
                paidBy.balance.set(transaction.user,amount*transaction.percentage/100)
            else
                paidBy.balance.set(transaction.user,amount*transaction.percentage/100+currentBalance)

            let takerBalance = transaction.user.balance.get(paidBy)
            if(takerBalance===undefined)
                transaction.user.balance.set(paidBy,-amount*transaction.percentage/100)
            else
                transaction.user.balance.set(paidBy,takerBalance-(amount*transaction.percentage/100))
        }
    }
}

class expensesWithExact extends expense {
    constructor(paidBy, expenseType, amount) {
        super(paidBy, expenseType, amount)
        this.payments = []
    }
    addUserWithAmount(taker,amountTaken) {
        this.payments.push({user: taker, amountTaken: amountTaken})
    }
    split() {
        const transactions = this.payments
        const amount = this.amount
        const paidBy = this.paidBy
        let sum = 0
        for(let transaction of transactions) {
            sum+=transaction.amountTaken
        }
        if(sum!==amount)
            throw "exception :: sumofAmounts is not equals amount"

        for(let transaction of transactions) {
            let currentBalance = paidBy.balance.get(transaction.user)
            if(currentBalance===undefined)
                paidBy.balance.set(transaction.user,transaction.amountTaken)
            else
                paidBy.balance.set(transaction.user,transaction.amountTaken+currentBalance)

            let takerBalance = transaction.user.balance.get(paidBy)
            if(takerBalance===undefined)
                transaction.user.balance.set(paidBy,-transaction.amountTaken)
            else
                transaction.user.balance.set(paidBy,takerBalance-transaction.amountTaken)
        }
    }
}

function main() {

    let g1 = new group()
    let u1 = new user('Hardik1', 'xyz', '99')
    let u2 = new user('Hardik2', 'xyz', '99')
    let u3 = new user('Hardik3', 'xyz', '99')
    let u4 = new user('Hardik4', 'xyz', '99')

    g1.addUser(u1)
    g1.addUser(u2)
    g1.addUser(u3)
    g1.addUser(u4)


    // let transaction1 = new expensesWithExact(u1, 'EXACT', 1000)
    // transaction1.addUserWithAmount(u2,300)
    // transaction1.addUserWithAmount(u3,700)
    // g1.addExpense(transaction1)
    // transaction1.split()

    // let transaction2 = new expensesWithPercentage(u1, 'PERCENT', 1000)
    // transaction2.addUserWithPercentage(u2,50)
    // transaction2.addUserWithPercentage(u3,50)
    // g1.addExpense(transaction2)
    // transaction2.split()

}

main()