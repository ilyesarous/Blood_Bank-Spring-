package com.csys.template.factory;
import com.csys.template.domain.Stock;
import com.csys.template.dto.StateDTO;
import com.csys.template.dto.StockDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockFactory {

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static StockDTO stockToStockDTO(Stock stock){
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setBlood(stock.getBlood());
        stockDTO.setCode(stock.getCode());
        stockDTO.setUserCreate(stock.getUserCreate());
        stockDTO.setDateCreate(stock.getDateCreate());


        return stockDTO;

    }

    public static Stock stockDTOToStock(StockDTO stockDTO){
        Stock stock = new Stock();
        LocalDate d = LocalDate.now();
        stock.setId(stockDTO.getId());
        stock.setBlood(stockDTO.getBlood());
        stock.setCode(stockDTO.getCode());
        stock.setUserCreate(getUserAuthenticated());
        stock.setDateCreate(d);


        return stock ;
    }

    public static List<StockDTO> stocksToStocksDTO(List<Stock> stocks){
        List<StockDTO> stockDTOS = new ArrayList<StockDTO>();
        for(Stock patient : stocks){
            stockDTOS.add(stockToStockDTO(patient));
        }
        return stockDTOS;
    }

    public static List<Stock> stocksDTOToStocks(List<StockDTO> stockDTOS){
        List<Stock> stocks = new ArrayList<Stock>();
        for(StockDTO stockDTO : stockDTOS){
            stocks.add(stockDTOToStock(stockDTO));
        }
        return stocks;
    }
}
