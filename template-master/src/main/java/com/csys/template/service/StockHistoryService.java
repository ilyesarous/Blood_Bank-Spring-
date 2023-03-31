package com.csys.template.service;


import com.csys.template.domain.StockHistory;
import com.csys.template.dto.StockHistoryDTO;
import com.csys.template.factory.StockHistoryFactory;
import com.csys.template.repository.StockHistoryRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class StockHistoryService {
    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryService(StockHistoryRepository stockHistoryRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
    }

    @Transactional(readOnly = true)
    public List<StockHistoryDTO> findAll() {
        List<StockHistory> stocks = stockHistoryRepository.findAll();
        com.csys.template.util.Preconditions.checkBusinessLogique(stocks != null, "error.couldn't-find-stock");
        List<StockHistoryDTO> stockDTOS = StockHistoryFactory.stocksHistoryToStocksHistoryDTO(stocks);

        return stockDTOS;
    }

    @Transactional
    public StockHistoryDTO addStockHistory(StockHistoryDTO stockDTO){
        Preconditions.checkArgument (stockDTO != null, "Stock added!");
        StockHistory d =stockHistoryRepository.save(StockHistoryFactory.stockHistoryDTOToStockHistory(stockDTO));
        return StockHistoryFactory.stockHistoryToStockHistoryDTO(d);
    }
}
