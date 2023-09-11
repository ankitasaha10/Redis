package com.example.redisproject.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.redisproject.entity.Invoice;
import com.example.redisproject.exception.InvoiceNotFoundException;
import com.example.redisproject.repository.InvoiceRepository;
import com.example.redisproject.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  @CachePut(cacheNames = "Invoice", key = "#inv.invId")
  @Override
  public Invoice saveInvoice(Invoice inv) {
    return invoiceRepository.save(inv);
  }

  @SuppressWarnings("unchecked")
  @Override
  @CachePut(cacheNames = "Invoice", key = "#invId")
  public Invoice updateInvoice(Invoice inv, Integer invId)
      throws JsonProcessingException, ParseException {
    Invoice invoice = getInvoice(invId);
    JSONObject invoiceObject =
        (JSONObject) new JSONParser().parse(new ObjectMapper().writeValueAsString(invoice));
    JSONObject invoicePayload =
        (JSONObject) new JSONParser().parse(new ObjectMapper().writeValueAsString(inv));
    for (Object obj : invoicePayload.keySet()) {
      String param = String.class.cast(obj);
      invoiceObject.put(param, invoicePayload.get(param));
    }
    invoice = new ObjectMapper().readValue(invoiceObject.toJSONString(), Invoice.class);
    return invoiceRepository.save(invoice);

  }

  @Override
  @Cacheable(value = "Invoice", key = "#invId")
  public Invoice getInvoice(Integer invId) {
    return invoiceRepository.findById(invId)
        .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
  }

  @Override
  public Object getAllInvoices(Pageable pageable) {
    return invoiceRepository.findAll(pageable);
  }

  @Override
  @CacheEvict(cacheNames = "Invoice", key = "#invId")
  public void deleteInvoice(Integer invId) {
    Invoice invoice = getInvoice(invId);
    invoiceRepository.delete(invoice);
  }
}
