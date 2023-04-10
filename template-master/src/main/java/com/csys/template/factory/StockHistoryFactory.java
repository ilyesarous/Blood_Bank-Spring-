package com.csys.template.factory;

import com.csys.template.domain.Stock;
import com.csys.template.domain.StockHistory;
import com.csys.template.dto.StockDTO;
import com.csys.template.dto.StockHistoryDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockHistoryFactory {

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static StockHistoryDTO stockHistoryToStockHistoryDTO(StockHistory stockHistory){
        StockHistoryDTO stockDTO = new StockHistoryDTO();
        stockDTO.setId(stockHistory.getId());
        stockDTO.setBlood(stockHistory.getBlood());
        stockDTO.setCode(stockHistory.getCode());
        stockDTO.setCodedonateur(stockHistory.getCodedonateur());
        stockDTO.setUserCreate(stockHistory.getUserCreate());
        stockDTO.setQuantite(stockHistory.getQuantite());
        LocalDate d= stockHistory.getDateCreate();
        String x=d.toString();
        stockDTO.setDateCreate(x);
        stockDTO.setDateperime(stockHistory.getDateperime());
        stockDTO.setService(stockHistory.getService());


        return stockDTO;

    }

    public static StockHistory stockHistoryDTOToStockHistory(StockHistoryDTO stockDTO){
        StockHistory stock = new StockHistory();
        LocalDate d = LocalDate.now();
        String  x =d.toString();
        String [] tab = x.split("-");
        int y= Integer.parseInt(tab[1]);
        int z=Integer.parseInt(tab[0]);
        y=y+3;
        if (y>12)
        {
            y=y-12;
            z=z+1;
        }
        tab[0]= Integer.toString(z);
        tab[1]= Integer.toString(y);
        String v= tab[0]+"-"+tab[1]+"-"+tab[2];

        stock.setId(stockDTO.getId());
        stock.setBlood(stockDTO.getBlood());
        stock.setCode(stockDTO.getCode());
        stock.setCodedonateur(stockDTO.getCodedonateur());
        stock.setUserCreate(getUserAuthenticated());
        stock.setQuantite(stockDTO.getQuantite());
        stock.setDateCreate(d);
        stock.setDateperime(v);
        stock.setService(stockDTO.getService());

        return stock ;
    }

    public static List<StockHistoryDTO> stocksHistoryToStocksHistoryDTO(List<StockHistory> stocks){
        List<StockHistoryDTO> stockDTOS = new ArrayList<StockHistoryDTO>();
        for(StockHistory p : stocks){
            stockDTOS.add(stockHistoryToStockHistoryDTO(p));
        }
        return stockDTOS;
    }

    public static List<StockHistory> stocksHistoryDTOToStockHistorys(List<StockHistoryDTO> stockDTOS){
        List<StockHistory> stocks = new ArrayList<StockHistory>();
        for(StockHistoryDTO stockDTO : stockDTOS){
            stocks.add(stockHistoryDTOToStockHistory(stockDTO));

        }
        return stocks;
    }
}
