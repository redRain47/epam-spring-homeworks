package ua.redrain47.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ua.redrain47.integration.model.Package;
import ua.redrain47.integration.model.*;
import ua.redrain47.integration.utils.CharacterStreamWritingMessageHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
public class IntegrationApplication {

    private AtomicInteger DtsPackageCounter = new AtomicInteger();
    private AtomicInteger DthPackageCounter = new AtomicInteger();
    private AtomicInteger TransferPackageCounter = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(IntegrationApplication.class, args);
        DeliveryService deliveryService = ctx.getBean(DeliveryService.class);

        for (int i = 1; i <= 100; i++) {
            Order order = new Order(i);
            order.addItem(DeliveryType.DTS);
            order.addItem(DeliveryType.DTH);
            order.addItem(DeliveryType.TRANSFER);
            deliveryService.placeOrder(order);
        }

        System.out.println("Hit 'Enter' to terminate");
        System.in.read();
        ctx.close();
    }

    private static void sleepUninterruptedly(long sleepFor, TimeUnit unit) {
        boolean interrupted = false;

        try {
            unit.sleep(sleepFor);
        } catch (InterruptedException e) {
            interrupted = true;
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public IntegrationFlow orders() {
        return f -> f
                .split(Order.class, Order::getOrderItems)
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .<OrderItem, DeliveryType>route(OrderItem::getDeliveryType, mapping -> mapping
                        .subFlowMapping(DeliveryType.DTH, sf -> sf
                                .channel(c -> c.queue(10))
                                .publishSubscribeChannel(c -> c
                                        .subscribe(s -> s.handle(m -> sleepUninterruptedly(1, TimeUnit.SECONDS)))
                                        .subscribe(sub -> sub
                                                .<OrderItem, String>transform(p ->
                                                        Thread.currentThread().getName() +
                                                                "  handling delivery package #" +
                                                                this.DthPackageCounter.incrementAndGet() +
                                                                " for order #" + p.getOrderNumber() + ": " + p)
                                                .handle(m -> System.out.println(m.getPayload()))))
                                .bridge())
                        .subFlowMapping(DeliveryType.DTS, sf -> sf
                                .channel(c -> c.queue(10))
                                .publishSubscribeChannel(c -> c
                                        .subscribe(s -> s.handle(m -> sleepUninterruptedly(5, TimeUnit.SECONDS)))
                                        .subscribe(sub -> sub
                                                .<OrderItem, String>transform(p ->
                                                        Thread.currentThread().getName() +
                                                                "  handling delivery package #" +
                                                                this.DtsPackageCounter.incrementAndGet() +
                                                                " for order #" + p.getOrderNumber() + ": " + p)
                                                .handle(m -> System.out.println(m.getPayload()))))
                                .bridge())
                        .subFlowMapping(DeliveryType.TRANSFER, sf -> sf
                                .channel(c -> c.queue(10))
                                .<OrderItem, String>transform(p ->
                                        Thread.currentThread().getName() +
                                                "  handling delivery package #" +
                                                this.TransferPackageCounter.incrementAndGet() +
                                                " for order #" + p.getOrderNumber() + ": " + p)
                                .handle(m -> System.out.println(m.getPayload()))))
                .<OrderItem, ua.redrain47.integration.model.Package>transform(orderItem ->

                        new ua.redrain47.integration.model.Package(orderItem.getOrderNumber(),
                                orderItem.getDeliveryType()
                        ))
                .aggregate(aggregator -> aggregator
                        .outputProcessor(g ->
                                new Delivery(g.getMessages()
                                        .stream()
                                        .map(message -> (ua.redrain47.integration.model.Package) message.getPayload())
                                        .collect(Collectors.toList())))
                        .correlationStrategy(m -> ((Package) m.getPayload()).getOrderNumber()))
                .handle(CharacterStreamWritingMessageHandler.stdout());
    }

    @MessagingGateway
    public interface DeliveryService {
        @Gateway(requestChannel = "orders.input")
        void placeOrder(Order order);
    }

}
