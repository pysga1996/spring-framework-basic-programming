package com.vengeance.authserver.controller;

import com.vengeance.authserver.dao.CustomerDAO;
import com.vengeance.authserver.model.Customer;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {


  private final CustomerDAO customerDAO;

  @Autowired
  public CustomerController(CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  @GetMapping("/")
  public String index() {
    return "external";
  }

  @GetMapping("/customers")
  public String customers(Principal principal, Model model) {
    addCustomers();
    Iterable<Customer> customerList = customerDAO.findAll();
    model.addAttribute("customers", customerList);
    model.addAttribute("username", principal.getName());
    return "customers";
  }

  // add customers for demonstration
  public void addCustomers() {

    Customer customer1 = new Customer();
    customer1.setAddress("1111 foo blvd");
    customer1.setName("Foo Industries");
    customer1.setServiceRendered("Important services");
    customerDAO.save(customer1);

    Customer customer2 = new Customer();
    customer2.setAddress("2222 bar street");
    customer2.setName("Bar LLP");
    customer2.setServiceRendered("Important services");
    customerDAO.save(customer2);

    Customer customer3 = new Customer();
    customer3.setAddress("33 main street");
    customer3.setName("Big LLC");
    customer3.setServiceRendered("Important services");
    customerDAO.save(customer3);
  }
}
