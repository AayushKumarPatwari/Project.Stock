package com.StockPortfolioApplication.StockPortfolio.Controller;

import com.StockPortfolioApplication.StockPortfolio.DTO.StockDTO;
import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import com.StockPortfolioApplication.StockPortfolio.Service.StockService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stock-portfolio-analysis/api/v1")
public class DetailsAPI {
    @Autowired
    private StockService stockRepository;

    @GetMapping("/{stockId}")
    public ResponseEntity<?> getStockDetails(@PathVariable("stockId") String stockID){
        //if (StringUtils.isEmpty(stockID))
        if (stockID == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseStatus("Failed", "Stock ID cannot be empty!"));
        }
        try{
            Optional<StockDTO> stock=stockRepository.findById(stockID);
            StockDTO stockResponse;
            stockResponse=stock.get();
            if(stock.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatus("Failed", "Stock ID is not valid"));
            }

            return ResponseEntity.ok().body(stockResponse);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatus("Failed", e.getMessage()));
        }
    }
}
