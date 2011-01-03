package shz.eprocurement.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Test_Customer {

    @Test
    public void should_Have_Different_HashCodes_With_Different_Contents() {
        // Given
        Customer customerA = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");
        Customer customerB = new Customer("00002", "Wyat", "Earp",
                "Somewhere else in the wild wild west");

        // Then
        assertThat(customerA.hashCode(), not(equalTo(customerB.hashCode())));
    }

    @Test
    public void should_Have_Same_HashCodes_With_Same_Contents() {
        // Given
        Customer customerA = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");
        Customer customerB = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");

        // Then
        assertThat(customerA.hashCode(), equalTo(customerB.hashCode()));
    }

    @Test
    public void should_Be_Different_With_Different_Contents() {
        // Given
        Customer customerA = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");
        Customer customerB = new Customer("00002", "Wyat", "Earp",
                "Somewhere else in the wild wild west");

        // Then
        assertThat(customerA, not(equalTo(customerB)));
    }

    @Test
    public void should_Be_Equal_With_Same_Contents() {
        // Given
        Customer customerA = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");
        Customer customerB = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");

        // Then
        assertThat(customerA, equalTo(customerB));
    }

    @Test
    public void should_Be_Equal_With_Same_Object() {
        // Given
        Customer customer = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");

        // Then
        assertThat(customer, equalTo(customer));
    }

    @Test
    public void should_Build_To_String_For_Full_Product() {
        // Given
        Customer customer = new Customer("00001", "Billy", "The Kid",
                "Somewhere in the wild wild west");

        // Then
        assertThat(
                customer.toString(),
                equalTo("Customer[_id=00001,_firstName=Billy,_lastName=The Kid,_address=Somewhere in the wild wild west]"));
    }

    @Test
    public void should_Build_To_String_For_Non_Full_Product() {
        // Given
        Customer customer = new Customer("00001");

        // Then
        assertThat(
                customer.toString(),
                equalTo("Customer[_id=00001,_firstName=<null>,_lastName=<null>,_address=<null>]"));
    }
}
