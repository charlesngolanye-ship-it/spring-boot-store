package com.codewithmosh.store.payments;

import com.codewithmosh.store.orders.Order;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckOutSession(Order order);
    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
