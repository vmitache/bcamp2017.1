

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import curs.banking.business.CustomerService;
import curs.banking.dao.CityDAO;
import curs.banking.model.Address;
import curs.banking.model.City;
import curs.banking.model.Customer;
import curs.banking.model.SexEnum;

public class CustomerServiceTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCreateCustomer() throws Exception {
    CustomerService cs = new CustomerService();
    Connection c = cs.getConnection();
    City city = new CityDAO(c).findById(1);
    c.close();
    ///
    Address addr = new Address();
    addr.setCity(city);
    addr.setStreet("Pafnutie street");
    addr.setNumber("66666");
    addr.setPostalCode("PC");
    Customer customer = new Customer();
    customer.setAddress(addr);
    customer.setName("Pafnutie gicu");
    customer.setSex(SexEnum.M);
    customer.setSSN("xxxxxxxxxxxx");
    customer.setVarsta(44);
    cs.createCustomer(customer, true);
  }

}
