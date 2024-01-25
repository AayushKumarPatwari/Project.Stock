package com.StockPortfolioApplication.StockPortfolio.Controller;

import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import com.StockPortfolioApplication.StockPortfolio.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/stock-portfolio-analysis")
public class UpdateAPI {
    @Autowired
    private StockService stockRepository;


    @PostMapping("/api/v1/{StockFileName}")
    public ResponseEntity<ResponseStatus> updateStockAPI(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        return stockRepository.UpdateStock(file);
    }

}
