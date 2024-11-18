package org.gabriel_dominguez.sgi.models;

import java.util.Date;

public class Payment {
  protected Integer id;

  private Float amount;

  private Float credit;

  private Date date;

  private Date dueDate;

  private Contract contract;

  public Payment() {
  }

  public Payment(Integer id, Float amount, Float credit, Date date, Date dueDate, Contract contract) {
    this.id = id;
    this.amount = amount;
    this.credit = credit;
    this.date = date;
    this.dueDate = dueDate;
    this.contract = contract;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Float getCredit() {
    return credit;
  }

  public void setCredit(Float credit) {
    this.credit = credit;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Contract getContract() {
    return contract;
  }

  public void setContract(Contract contract) {
    this.contract = contract;
  }
}
