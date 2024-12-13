// A typical entity class (a 'POJO' - Plain Old Java Object)

import java.util.Objects;

class Account implements Cloneable {
  private final int id;
  private final String holder;
  private int balance;

  public Account(int id, String holder, int balance) {
    this.id = id;
    this.holder = holder;
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  public String getHolder() {
    return holder;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return String.format("Account(id=%d, holder=%s, balance=%d)",
      id, holder, balance);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    else if (other instanceof Account) {
      Account otherAccount = (Account) other;
      return (id == otherAccount.id)
          && holder.equals(otherAccount.holder)
          && (balance == otherAccount.balance);
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, holder, balance);
  }

  public static void main(String[] args) {
    Account account = new Account(101, "Nick", 1200);
    System.out.println(account);
    System.out.println("Withdrawing money...");
    account.setBalance(750);
    System.out.println(account);
  }
}
