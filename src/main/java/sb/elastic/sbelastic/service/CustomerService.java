package sb.elastic.sbelastic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sb.elastic.sbelastic.model.Customer;
import sb.elastic.sbelastic.repository.CustomerRepository;


@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        long cekCustomer = customerRepository.count();
        if(cekCustomer>0){
            customer.setId(cekCustomer+1);
            customerRepository.save(customer);
        }else{
            customer.setId(1);
            customerRepository.save(customer);    
        }
        return customer;
    }

    public List<Customer> getAllCustomers(){
        Page<Customer> list = customerRepository.findAll(Pageable.ofSize(10));
        return list.toList();
    }

    public Customer updateCustomer(long id, Customer customer){
        Customer cekCustomer = customerRepository.findById(id).get();
        Customer updateCustomer = new Customer();
        if(cekCustomer!=null){
            updateCustomer = customerRepository.save(customer);
        }
        return updateCustomer;
    }

    public Customer getCustomer(long id){
        return customerRepository.findById(id).get();
    }

    public boolean deleteCustomer(long id){
        boolean customerExists = customerRepository.existsById(id);
        if(customerExists==true){
            customerRepository.deleteById(id);

        }
        return customerExists;
    }

}
