package com.csys.template.service;
import com.csys.template.domain.Patient;
import com.csys.template.domain.Stock;
import com.csys.template.dto.*;
import com.csys.template.factory.PatientFactory;
import com.csys.template.factory.StockFactory;
import com.csys.template.repository.StockRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.csys.template.TemplateApplication.log;

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
        log.debug("*** find Stock By Code ***");
        Stock stock = stockRepository.findBycode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(stock != null,"stock does  Not found!");
        StockDTO stockDTO = StockFactory.stockToStockDTO(stock);

        return stockDTO;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll(Specification<Stock> stockSpecification) {
        log.debug("*** find All stock ***");
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
        log.debug("*** find By blood ***");
       List <Stock> stock = stockRepository.findBybloodCode(blood);
        com.csys.template.util.Preconditions.checkBusinessLogique(stock != null,"stock does  Not found!");
        List<StockDTO> stockDTO = StockFactory.stocksToStocksDTO(stock);

        return stockDTO;
    }
    @Transactional
    public StockDTO addStock(StockDTO stockDTO){
        log.debug("*** add Stock ***");

        CounterDTO counter = counterService.findCounterByType("stock");
        stockDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        String blood =stockDTO.getBlood();
        String bloodcode=bloodService.findBloodCodeByType(blood).toString();
        Integer s=bloodService.findBloodCodeByType(blood);
        stockDTO.setBlood(bloodcode);
        StockHistoryDTO stockHistoryDTO= StockFactory.stockToStockHistoryDTO(stockDTO);
        stockHistoryService.addStockHistory(stockHistoryDTO);
        //quantiter totale


        Stock d =stockRepository.save(StockFactory.stockDTOToStock(stockDTO));
        return StockFactory.stockToStockDTO(d);
    }

    @Transactional
    public StockDTO update(StockDTO stockDTO){
        log.debug("*** remove  donation in stock ***");
        Stock stock = stockRepository.findBycode(stockDTO.getCode());
        stockDTO.setBlood(stockDTO.getBlood());
        stockDTO.setUserCreate(stock.getUserCreate());
        stockDTO.setCode(stock.getCode());
        stockDTO.setVersion(stock.getVersion());
        stockDTO.setQuantite(0);
        stockDTO.setDateperime(stock.getDateperime());
        stockDTO.setCodedonateur(stock.getCodedonateur());
        stockDTO.setId(stock.getId());

        StockHistoryDTO stockHistoryDTO= StockFactory.DELstockToStockHistoryDTO(stockDTO);
        stockHistoryService.addStockHistory(stockHistoryDTO);
        stockRepository.save(StockFactory.stockDTOToStock(stockDTO));

        return stockDTO;
    }
    @Transactional
    public StockDTO remove(String code){
        log.debug("*** remove  donation in stock ***");
        Stock stock = stockRepository.findBycode(code);
        StockDTO stockDTO =StockFactory.stockToStockDTO(stock);
        StockHistoryDTO stockHistoryDTO= StockFactory.DELstockToStockHistoryDTO(stockDTO);
        stockHistoryService.addStockHistory(stockHistoryDTO);
        stockRepository.deleteById(stock.getId().toString());

        return stockDTO;
    }

    public Integer getQantiteTotal(Integer blood){
        log.debug("*** get Qantite Total for blood ***");
        List<Stock> stocks = stockRepository.findBybloodCode(blood);
        return stocks.toArray().length;
    }

}
