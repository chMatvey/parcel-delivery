package com.github.chmatvey.order.client.service.impl;

import com.github.chmatvey.order.client.dto.OrderCreateRequest;
import com.github.chmatvey.order.client.dto.OrderCreateResponse;
import com.github.chmatvey.order.client.service.OrderClientServiceImpl;
import com.github.chmatvey.order.common.entity.Order;
import com.github.chmatvey.order.common.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.chmatvey.order.common.entity.OrderStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderClientServiceImpl.class)
class OrderClientServiceImplTest {
    @Autowired
    OrderClientServiceImpl orderClientService;

    @MockBean
    OrderRepository orderRepository;

    @Test
    void createOrder() {
        // Given
        long userId = 60224;
        OrderCreateRequest request = OrderCreateRequest.builder()
                .details("Laptop delivery")
                .origin("Suite 781 30176 Dwain Ville, North Charlesfurt, ME 94096")
                .destination("Apt. 616 8940 Yundt Pines, Port Gerald, RI 27315")
                .build();
        Order expectedOrder = Order.builder()
                .userId(userId)
                .status(CREATED)
                .details(request.details())
                .origin(request.origin())
                .destination(request.destination())
                .build();

        long orderId = 1024;
        Order order = mock(Order.class);
        when(orderRepository.save(expectedOrder)).thenReturn(order);
        when(order.getId()).thenReturn(orderId);


        // When
        OrderCreateResponse response = orderClientService.createOrder(request, userId);

        // Then
        verify(orderRepository).save(expectedOrder);
        assertEquals(orderId, response.orderId());
    }
}