package com.csys.template.web.rest;
import com.csys.template.dto.StockDTO;
import com.csys.template.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/stock")
public class StockResource {

    private final StockService stockService;

    public StockResource(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping()
    public List<StockDTO> getAll(){
        List<StockDTO> stockDTOS = stockService.findAll();
        return stockDTOS;
    }
    @GetMapping("/{code}")
    public StockDTO getByCode( @PathVariable @Valid String code){
        StockDTO stockDTOS = stockService.findStockByCode(code);
        return stockDTOS;
    }

    @PostMapping
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(stockDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "code", " You can not add stock with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        StockDTO p = stockService.addStock(stockDTO);
        return ResponseEntity.created(new URI("/stock"+ p.getCode())).body(p);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<StockDTO> DeletStock(@PathVariable @RequestBody String code)
            throws  URISyntaxException {

        StockDTO p = stockService.remove(code);
        return ResponseEntity.created(new URI("/stock"+ p.getCode())).body(p);
    }
}
