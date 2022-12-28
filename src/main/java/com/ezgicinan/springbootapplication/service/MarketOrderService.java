package com.ezgicinan.springbootapplication.service;

import com.ezgicinan.springbootapplication.model.Customer;
import com.ezgicinan.springbootapplication.model.MarketOrder;
import com.ezgicinan.springbootapplication.model.Product;
import com.ezgicinan.springbootapplication.repository.MarketOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MarketOrderService {

    @Autowired
    private final MarketOrderRepository marketOrderRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public MarketOrderService(MarketOrderRepository marketOrderRepository, ProductService productService, CustomerService customerService) {
        this.marketOrderRepository = marketOrderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public MarketOrder insertMarketOrder(List<Integer> productIdList, Integer customerId, String orderDay){
        Double totalCost = 0.0;
        Customer customer = customerService.getCustomerById(customerId);

        for(Integer productId : productIdList){
            Product product = productService.getProductById(productId).get();

            if(product.getStock() > 0 && customer.getCustomerMoney() >= totalCost && customer.getCustomerMoney() >= product.getPrice()){
                totalCost = totalCost + product.getPrice();
                customer.setToBePaidMoney(totalCost);
                product.setStock(product.getStock()-1);
            }else{
                System.out.println("Out of stock or Out of Money");
            }
        }

        String changeByWords = Double.toString(customer.getCustomerChange());
        System.out.println(getMoneyIntoWords(changeByWords));
        customer.setCustomerChange(customer.getCustomerMoney() - totalCost);
        customer.setMoneyChangeByWords(getMoneyIntoWords(changeByWords));

        MarketOrder marketOrder = MarketOrder.builder()
                .customerId(customerId)
                .productList(productIdList)
                .totalPrice(totalCost)
                .orderDay(orderDay)
                .build();
        return marketOrderRepository.save(marketOrder);
    }

    public List<MarketOrder> getAllMarketOrders () {
        return marketOrderRepository.findAll();
    }

    public Map<String, Double> getProfit (List<Integer> marketOrderIdList) {
        Map<String, Double> dailyProfitMap = new HashMap<String, Double>();

        for(Integer orderId : marketOrderIdList){
            MarketOrder marketOrder = getMarketOrderById(orderId).get();
            if (dailyProfitMap.containsKey(marketOrder.getOrderDay())) {
                double currentTotalPrice = dailyProfitMap.get(marketOrder.getOrderDay());
                dailyProfitMap.put(marketOrder.getOrderDay(), currentTotalPrice + marketOrder.getTotalPrice());
            }else {
                dailyProfitMap.put(marketOrder.getOrderDay(), marketOrder.getTotalPrice());
            }
        }

        return dailyProfitMap;
    }

    public Optional<MarketOrder> getMarketOrderById(Integer marketOrderId){
        return  marketOrderRepository.findById(marketOrderId);
    }

    public List<Integer> getAllMarketOrderId (List<MarketOrder> marketOrderList){
        List<Integer> marketOrderIdList = new ArrayList<>();
        for(MarketOrder marketOrder: marketOrderList){
            marketOrderIdList.add(marketOrder.getId());
        }

        return marketOrderIdList;
    }

    public String getMoneyIntoWords(String input) {
        MoneyConverters converter = MoneyConverters.TURKISH_BANKING_MONEY_VALUE;
        return converter.asWords(new BigDecimal(input));
    }

}
