package com.csys.template.web.rest;

import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.StockHistoryDTO;
import com.csys.template.service.StockHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stockHistory")
public class StockHistoryResource {

    private final StockHistoryService stockHistoryService;

    public StockHistoryResource(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping
    public List<StockHistoryDTO> getAll(){
        return stockHistoryService.findAll();
    }


}
