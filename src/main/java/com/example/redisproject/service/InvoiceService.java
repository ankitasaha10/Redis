package com.example.redisproject.service;

import com.example.redisproject.entity.Invoice;

public interface InvoiceService {

  public Invoice saveInvoice(Invoice inv);

  public Invoice updateInvoice(Invoice inv, Integer invId);

  public void deleteInvoice(Integer invId);

//  public Object getAllInvoices();

}
