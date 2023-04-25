package com.csys.template.service;
import com.csys.template.domain.Patient;
import com.csys.template.domain.Stock;
import com.csys.template.dto.*;
import com.csys.template.factory.PatientFactory;
import com.csys.template.factory.StockFactory;
import com.csys.template.repository.StockRepository;
import com.google.common.base.Preconditions;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockService {
    private final StockRepository stockRepository;
    private final CounterService counterService;
    private final StockHistoryService stockHistoryService;
    private final BloodService bloodService;

    public StockService(StockRepository stockRepository, CounterService counterService, StockHistoryService stockHistoryService, BloodService bloodService) {
        this.stockRepository = stockRepository;
        this.counterService = counterService;
        this.stockHistoryService = stockHistoryService;
        this.bloodService = bloodService;
    }

//    @Transactional(readOnly = true)
//    public List<StockDTO> findAll(){
//        List<Stock> stocks = stockRepository.findAll();
//        return StockFactory.stocksToStocksDTO(stocks);
//    }

//    @Transactional(readOnly = true)
//    public StockDTO findStockByCode(String code) {
//        Stock stock = stockRepository.findBycode(code);
//        com.csys.template.util.Preconditions.checkBusinessLogique(stock != null,"stock does  Not found!");
//        StockDTO stockDTO = StockFactory.stockToStockDTO(stock);
//
//        return stockDTO;
//    }

    @Transactional(readOnly = true)
    public StockDTO findStockByCode(String code) {
        Stock stock = stockRepository.findBycode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(stock != null,"stock does  Not found!");
        StockDTO stockDTO = StockFactory.stockToStockDTO(stock);

        return stockDTO;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll(Specification<Stock> stockSpecification) {
//        List<Stock> stocks = stockRepository.findAll(stockSpecification);
//        com.csys.template.util.Preconditions.checkBusinessLogique(stocks != null, "error.couldn't-find-stock");
//        List<StockDTO> stockDTOS = StockFactory.stocksToStocksDTO(stocks);
//
//        return stockDTOS;


        List<Stock> stocks = stockRepository.findAll(stockSpecification);
        com.csys.template.util.Preconditions.checkBusinessLogique(stocks!=null,"error patient does not found");
        List<Integer> bloodCodes = stocks.stream()
                .map(Stock::getBlood)
                .distinct()
                .collect(Collectors.toList());
        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
        List<StockDTO> stockDTOS = new ArrayList<>();
        stocks.forEach(p -> {
            StockDTO stockDTO = StockFactory.stockToStockDTO(p);
            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
                    .filter(b -> b.getCodeBlood().compareTo(p.getBlood()) == 0)
                    .findFirst();
            if (bloodDTOOptional.isPresent()) {
                stockDTO.setBlood(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
            }
            stockDTOS.add(stockDTO);
        });

        return stockDTOS;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByblood(Integer blood) {
       List <Stock> stock = stockRepository.findBybloodCode(blood);
        com.csys.template.util.Preconditions.checkBusinessLogique(stock != null,"stock does  Not found!");
        List<StockDTO> stockDTO = StockFactory.stocksToStocksDTO(stock);

        return stockDTO;
    }
    @Transactional
    public StockDTO addStock(StockDTO stockDTO){
        Preconditions.checkArgument (stockDTO != null, "Stock added!");
        CounterDTO counter = counterService.findCounterByType("stock");
        stockDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        String blood =stockDTO.getBlood();
        String bloodcode=bloodService.findBloodCodeByType(blood).toString();
        stockDTO.setBlood(bloodcode);
        StockHistoryDTO stockHistoryDTO= StockFactory.stockToStockHistoryDTO(stockDTO);
        stockHistoryService.addStockHistory(stockHistoryDTO);
        //quantiter totale
//        stockDTO.setQuantiteTotal(getQantiteTotal(stockDTO.getBlood()));

        Stock d =stockRepository.save(StockFactory.stockDTOToStock(stockDTO));
        return StockFactory.stockToStockDTO(d);
    }
    @Transactional
    public StockDTO remove(String code){
        Stock stock = stockRepository.findBycode(code);
        StockDTO stockDTO =StockFactory.stockToStockDTO(stock);
        StockHistoryDTO stockHistoryDTO= StockFactory.DELstockToStockHistoryDTO(stockDTO);
        stockHistoryService.addStockHistory(stockHistoryDTO);
        stockRepository.deleteById(stock.getId().toString());

        return stockDTO;
    }

    public Integer getQantiteTotal(Integer blood){
        List<Stock> stocks = stockRepository.findBybloodCode(blood);
        return stocks.toArray().length;
    }

}
