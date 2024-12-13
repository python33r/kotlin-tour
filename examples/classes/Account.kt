// Kotlin equivalent of Account.java

data class Account(val id: Int, val holder: String, var balance: Int)

fun main() {
    val account = Account(101, "Nick", 1200)
    println(account)
    println("Withdrawing money...")
    account.balance = 750
    println(account)
}
