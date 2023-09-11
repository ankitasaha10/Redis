package com.example.redisproject.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.redisproject.entity.Invoice;
import com.example.redisproject.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/v1.0/invoices")
public class InvoiceController {

  @Autowired
  InvoiceService invoiceService;

  @PostMapping
  public Invoice saveInvoice(@RequestBody Invoice inv) {
    return invoiceService.saveInvoice(inv);
  }

  @GetMapping
  public ResponseEntity<?> getAllInvoices(
      @PageableDefault(page = 0, value = 10) Pageable pageable) {
    return ResponseEntity.ok(invoiceService.getAllInvoices(pageable));
  }

  @GetMapping("/getOne/{id}")
  public Invoice getOneInvoice(@PathVariable Integer id) {
    return invoiceService.getInvoice(id);
  }

  @PutMapping("/{id}")
  public Invoice updateInvoice(@RequestBody Invoice inv, @PathVariable Integer id)
      throws JsonProcessingException, ParseException {
    return invoiceService.updateInvoice(inv, id);
  }

  @DeleteMapping("/{id}")
  public String deleteInvoice(@PathVariable Integer id) {
    invoiceService.deleteInvoice(id);
    return "Employee with id: " + id + " Deleted !";
  }
}
