package ru.fokin.paymentservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fokin.paymentservice.dto.PaymentRequest;
import ru.fokin.paymentservice.dto.PaymentResponse;
import ru.fokin.paymentservice.dto.ProductResponse;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        try {
            ProductResponse product = restTemplate.getForObject("/api/products/" + request.getProductId(), ProductResponse.class);

            if (product == null) {
                throw new RuntimeException("Product not found");
            }

            if (product.getBalance() < request.getAmount()) {
                throw new RuntimeException("Insufficient funds");
            }

            product.setBalance(product.getBalance() - request.getAmount());

            PaymentResponse response = new PaymentResponse();
            response.setStatus("SUCCESS");
            response.setMessage("Payment processed successfully");
            return response;
        } catch (Exception e) {
            PaymentResponse response = new PaymentResponse();
            response.setStatus("ERROR");
            response.setMessage(e.getMessage());
            return response;
        }
    }
}