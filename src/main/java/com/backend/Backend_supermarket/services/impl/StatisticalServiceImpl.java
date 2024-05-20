package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.models.Category;
import com.backend.Backend_supermarket.models.Order;
import com.backend.Backend_supermarket.models.OrderDetail;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.repositorys.CategoryRepository;
import com.backend.Backend_supermarket.repositorys.OrderDetailRepository;
import com.backend.Backend_supermarket.repositorys.OrderRepository;
import com.backend.Backend_supermarket.repositorys.ReceiptRepository;
import com.backend.Backend_supermarket.responses.StatisticsByCategoryResponses;
import com.backend.Backend_supermarket.responses.StatisticsOverviewResponses;
import com.backend.Backend_supermarket.services.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ReceiptRepository receiptRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public StatisticsOverviewResponses statisticsOverview(LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        StatisticsOverviewResponses responses = new StatisticsOverviewResponses();
        List<Order> orderList = orderRepository.findOrdersByCreatedAtBetween(startDate, endDate);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Float totalRevenue = 0.0f;
        for(var x : orderList) {
            orderDetailList.addAll(orderDetailRepository.findByOrderId(x.getId()));
            totalRevenue += x.getTotalMoney();
        }

        List<Receipt> receiptList = receiptRepository.findByNameAndStatusAndCreateAtBetween("", "", startDate, endDate);

        float totalCostOfProduct = 0f;
        for(var x : receiptList) {
            totalCostOfProduct += x.getTotalMoney();
        }

        responses.setTotalRevenue(totalRevenue);
        responses.setNumberOfOrders(orderDetailList.size());
        responses.setProfit(totalRevenue - totalCostOfProduct);

        return responses;
    }

    @Override
    public List<StatisticsByCategoryResponses> statisticsByCategory(LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        List<StatisticsByCategoryResponses> responsesList = new ArrayList<>();
        Map<Category, Integer> categoryResponsesMap = new HashMap<>();

        List<Category> categoryList = categoryRepository.findAll();

        for (int i = 0; i < categoryList.size(); i++) {
            StatisticsByCategoryResponses newStatisticsByCategoryResponses = new StatisticsByCategoryResponses();
            Category category = categoryList.get(i);
            newStatisticsByCategoryResponses.setCategory(category);
            newStatisticsByCategoryResponses.setRevenue(0f);
            newStatisticsByCategoryResponses.setBuyerNum(0);
            responsesList.add(newStatisticsByCategoryResponses);
            categoryResponsesMap.put(category, i);
        }


        List<Order> orderList = orderRepository.findOrdersByCreatedAtBetween(startDate, endDate);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Float totalRevenue = 0.0f;
        for(var x : orderList) {
            orderDetailList.addAll(orderDetailRepository.findByOrderId(x.getId()));
            totalRevenue += x.getTotalMoney();
        }

        for(var x : orderDetailList) {
            int idx = categoryResponsesMap.get(x.getProduct().getCategory());
            responsesList.get(idx).setRevenue(responsesList.get(idx).getRevenue() + (x.getProduct().getPrice() * x.getNumberOfProducts()));
            responsesList.get(idx).setBuyerNum(responsesList.get(idx).getBuyerNum() + 1);
        }

        for (var z : responsesList) {
            z.setPercentOfRevenue((z.getRevenue() / totalRevenue) * 100);
        }

        return responsesList;
    }
}









