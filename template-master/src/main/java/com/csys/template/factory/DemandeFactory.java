package com.csys.template.factory;

import com.csys.template.domain.Demande;
import com.csys.template.domain.Stock;
import com.csys.template.dto.DemandeDTO;
import com.csys.template.dto.StockDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemandeFactory {
    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static DemandeDTO stockToStockDTO(Demande demande){
        DemandeDTO demandeDTO = new DemandeDTO();
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setQuantiter(demande.getQuantiter());
        demandeDTO.setState(demande.getState());
        demandeDTO.setBlood(demande.getBlood());
        demandeDTO.setCreateDate(demande.getCreateDate());
        demandeDTO.setUsercreate(demande.getUsercreate());



        return demandeDTO;

    }

    public static Demande stockDTOToStock(DemandeDTO demandeDTO){

        Demande demande= new Demande();

        demande.setCode(demandeDTO.getCode());
        demande.setCodeMedecin(demandeDTO.getCodeMedecin());
        demande.setCodeService(demandeDTO.getCodeService());
        demande.setQuantiter(demandeDTO.getQuantiter());
        demande.setState(demandeDTO.getState());
        demande.setBlood(demandeDTO.getBlood());
        demande.setCreateDate(demandeDTO.getCreateDate());
        demande.setUsercreate(getUserAuthenticated());

        return demande ;
    }

//    public static List<StockDTO> stocksToStocksDTO(List<Stock> stocks){
//        List<StockDTO> stockDTOS = new ArrayList<StockDTO>();
//        for(Stock patient : stocks){
//            stockDTOS.add(stockToStockDTO(patient));
//        }
//        return stockDTOS;
//    }
//
//    public static List<Stock> stocksDTOToStocks(List<StockDTO> stockDTOS){
//        List<Stock> stocks = new ArrayList<Stock>();
//        for(StockDTO stockDTO : stockDTOS){
//            stocks.add(stockDTOToStock(stockDTO));
//
//        }
//        return stocks;
//    }
}
