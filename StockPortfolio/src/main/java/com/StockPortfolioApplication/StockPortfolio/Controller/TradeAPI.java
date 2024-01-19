package com.StockPortfolioApplication.StockPortfolio.Controller;

import com.StockPortfolioApplication.StockPortfolio.Entity.Trade;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import com.StockPortfolioApplication.StockPortfolio.Service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stock-portfolio-analysis")
public class TradeAPI {
    @Autowired
    public TradeService tradeService;
    @PostMapping("/api/v1/tradeAPI")
    public ResponseEntity<ResponseStatus> getTradeAPI(@RequestBody Trade trade){
        return tradeService.GetTradeDetail(trade);
    }
}
