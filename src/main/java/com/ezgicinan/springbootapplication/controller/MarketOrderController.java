package com.ezgicinan.springbootapplication.controller;

import com.ezgicinan.springbootapplication.model.MarketOrder;
import com.ezgicinan.springbootapplication.service.MarketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/marketOrder")
public class MarketOrderController {

    @Autowired
    private final MarketOrderService marketOrderService;

    public MarketOrderController(MarketOrderService marketOrderService) {
        this.marketOrderService = marketOrderService;
    }

    @PostMapping(path = "/addMarketOrder")
    public ResponseEntity<MarketOrder> insertMarketOrder(@RequestBody MarketOrder marketOrder){
        return ResponseEntity.ok(marketOrderService.insertMarketOrder(marketOrder.getProductList(), marketOrder.getCustomerId(), marketOrder.getOrderDay()));
    }

    //Get order
    @GetMapping("/getMarketOrders")
    public List<MarketOrder> getAllMarketOrders() {
        try {
            List<MarketOrder> allMarketOrders = marketOrderService.getAllMarketOrders();
            return  allMarketOrders;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getDailyProfit")
    public Map<String, Double> getDailyProfit() {
        try {
            List<MarketOrder> allMarketOrders = marketOrderService.getAllMarketOrders();
            Map<String, Double> allDailyProfit =
                    marketOrderService.getProfit(marketOrderService.getAllMarketOrderId(allMarketOrders));
            return  allDailyProfit;
        } catch (Exception e) {
            return null;
        }
    }

}
