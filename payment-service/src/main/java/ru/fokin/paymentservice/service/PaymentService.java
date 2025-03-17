package ru.fokin.paymentservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fokin.paymentservice.dto.PaymentRequest;
import ru.fokin.paymentservice.dto.PaymentResponse;
import ru.fokin.paymentservice.dto.ProductResponse;

@Service
public class PaymentService {

    @Value("${product.service.url}")
    private static String PRODUCT_SERVICE_URL;

    private final RestTemplate restTemplate;
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        try {

            ResponseEntity<ProductResponse> productResponse = restTemplate.getForEntity(
                    PRODUCT_SERVICE_URL + "/" + request.getProductId(), ProductResponse.class);

            if (!productResponse.getStatusCode().is2xxSuccessful() || productResponse.getBody() == null) {
                throw new RuntimeException("Product not found");
            }

            ProductResponse product = productResponse.getBody();

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
