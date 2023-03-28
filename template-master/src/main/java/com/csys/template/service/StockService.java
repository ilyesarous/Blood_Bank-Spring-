package com.csys.template.service;
import com.csys.template.domain.Stock;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.StockDTO;
import com.csys.template.factory.StockFactory;
import com.csys.template.repository.StockRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockService {
    private final StockRepository stockRepository;
    private final CounterService counterService;

    public StockService(StockRepository stockRepository, CounterService counterService) {
        this.stockRepository = stockRepository;
        this.counterService = counterService;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockDTO> stockDTOS = StockFactory.stocksToStocksDTO(stocks);

        return stockDTOS;
    }
    @Transactional(readOnly = true)
    public StockDTO findStockByCode(String code) {
        Stock stock = stockRepository.findBycode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(stock != null,"stock does  Not found!");
        StockDTO stockDTO = StockFactory.stockToStockDTO(stock);

        return stockDTO;
    }
    @Transactional
    public StockDTO addStock(StockDTO stockDTO){
        Preconditions.checkArgument (stockDTO != null, "Stock added!");
        CounterDTO counter = counterService.findCounterByType("stock");
        stockDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
//        Stock stock = new Stock();
        Stock d =stockRepository.save(StockFactory.stockDTOToStock(stockDTO));
        return StockFactory.stockToStockDTO(d);
    }
    @Transactional
    public StockDTO remove(String code){
        Stock stock = stockRepository.findBycode(code);
        StockDTO stockDTO =StockFactory.stockToStockDTO(stock);
        stockRepository.deleteById(stock.getId().toString());
        return stockDTO;
    }

}
