package org.gabriel_dominguez.sgi.models;

import java.util.Date;
import java.util.List;

public class Contract {

  protected Integer id;

  protected Date finishDate;

  protected Date startDate;

  protected Integer duration;

  protected Integer initialRentPrice;

  protected Integer actualRentPrice;

  protected Integer updatingPriceInterval;

  protected Tentant tentant;

  protected Guarantor guarantor;

  protected Property property;

  protected List<Payment> payments;
}
