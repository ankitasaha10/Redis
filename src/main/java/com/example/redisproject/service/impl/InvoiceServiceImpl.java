package com.example.redisproject.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.example.redisproject.entity.Invoice;
import com.example.redisproject.exception.InvoiceNotFoundException;
import com.example.redisproject.repository.InvoiceRepository;
import com.example.redisproject.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {


  @Autowired
  private InvoiceRepository invoiceRepository;

  @Override
  public Invoice saveInvoice(Invoice inv) {
    return invoiceRepository.save(inv);
  }

  @Override
  @CachePut(value = "Invoice", key = "#invId")
  public Invoice updateInvoice(Invoice inv, Integer invId) {
    Invoice invoice = invoiceRepository.findById(invId)
        .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
    invoice.setInvAmount(inv.getInvAmount());
    invoice.setInvName(inv.getInvName());
    return invoiceRepository.save(invoice);

  }

  // @Override
  // @Cacheable(value="Invoice", key="#invId.toString()")
  // public Invoice getOneInvoice(Integer invId) {
  // Invoice invoice=invoiceRepository.findById(invId)
  // .orElseThrow(()->new InvoiceNotFoundException("Invoice not found"));
  // return invoice;
  // }

  // @Override
  // @Cacheable(value="Invoice")
  // public List<Invoice> getAllInvoices() {
  // return invoiceRepository.findAll();
  // }

  @Override
  @CacheEvict(value = "Invoice", key = "#invId")
  // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple records to delete
  public void deleteInvoice(Integer invId) {
    Invoice invoice = invoiceRepository.findById(invId)
        .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
    invoiceRepository.delete(invoice);
  }
}
