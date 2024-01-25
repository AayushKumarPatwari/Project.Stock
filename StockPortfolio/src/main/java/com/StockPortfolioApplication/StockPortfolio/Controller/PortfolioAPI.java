package com.StockPortfolioApplication.StockPortfolio.Controller;

import com.StockPortfolioApplication.StockPortfolio.DTO.PortfolioDTO;
import com.StockPortfolioApplication.StockPortfolio.DTO.StockDTO;
import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import com.StockPortfolioApplication.StockPortfolio.Service.PortfolioService;
import com.StockPortfolioApplication.StockPortfolio.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock-portfolio-analysis")
public class PortfolioAPI {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/portfolio/v1/{userId}")
   // @Cacheable(value = "Portfolio" ,key = "#userId")
    public List<PortfolioDTO> getPortfolioDetails(@PathVariable("userId") int userId){
//        try{
            return portfolioService.getAllPortfolio(userId);
            //to change to return list

//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatus("Failed","Failure in getting portfolio-detail"));
//        }
    }
}
