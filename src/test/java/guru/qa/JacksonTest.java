package guru.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Orders;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        String ordersJson = "{\n" +
                " \"message\" : \"Successful\",\n" +
                " \"status\" : 200\n" +
                "}";

        Orders orders = objectMapper.readValue(ordersJson, Orders.class);

        assertThat(orders.getMessage()).isEqualTo("Successful");
    }

}
