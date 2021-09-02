package sb.elastic.sbelastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sb.elastic.sbelastic.model.Customer;
import sb.elastic.sbelastic.service.CustomerService;

@RestController
@RequestMapping("/sbelastic")
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/customer", method=RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> list = customerService.getAllCustomers();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value="/customer", method=RequestMethod.POST)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        Customer insertCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(insertCustomer);
    }

    @RequestMapping(value="/customer/{id}", method=RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id){
        Customer findCustomer = customerService.getCustomer(id);
        return ResponseEntity.ok(findCustomer);
    }

    @RequestMapping(value="/customer/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") long id){
        Customer findCustomer = customerService.getCustomer(id);
        Customer updateCustomer = new Customer();
        if(findCustomer!=null){
            updateCustomer = customerService.updateCustomer(id, findCustomer);
        }
        return ResponseEntity.ok(updateCustomer);
    }

    @RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") long id){
        boolean userExists = customerService.deleteCustomer(id);
        String deleteStatus = "";
        if(userExists==true){
            deleteStatus="user is deleted";
        }
        return ResponseEntity.ok(deleteStatus);
    }

}
