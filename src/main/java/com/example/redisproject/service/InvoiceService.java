package com.example.redisproject.service;

import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.redisproject.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface InvoiceService {

  public Invoice saveInvoice(Invoice inv);

  public Invoice updateInvoice(Invoice inv, Integer invId)
      throws JsonProcessingException, ParseException;

  public void deleteInvoice(Integer invId);

  public Invoice getInvoice(Integer invId);

  public Object getAllInvoices(Pageable pageable);
}
