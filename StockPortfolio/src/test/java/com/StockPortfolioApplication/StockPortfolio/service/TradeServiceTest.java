package com.StockPortfolioApplication.StockPortfolio.service;

import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import com.StockPortfolioApplication.StockPortfolio.Entity.Trade;
import com.StockPortfolioApplication.StockPortfolio.Repository.PortfolioRepository;
import com.StockPortfolioApplication.StockPortfolio.Repository.StockRepository;
import com.StockPortfolioApplication.StockPortfolio.Repository.TradeRepository;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import com.StockPortfolioApplication.StockPortfolio.Service.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    public void testGetTradeDetail_Sell() {
        // Arrange
        Trade trade = new Trade(1, "stockId", 5, "Sell");
        Stock stock = new Stock("stockId", "StockName", 100.0, 105.0, 110.0, 90.0, 105.0);
        Portfolio portfolio = new Portfolio(1, "stockId", "StockName", 100.0, 105.0, 10, 1000.0, 500.0);

        when(stockRepository.findByStockId("stockId")).thenReturn(stock);
        when(portfolioRepository.findByUserIdAndStockId(1, "stockId")).thenReturn(portfolio);

        ResponseEntity<ResponseStatus> result = tradeService.GetTradeDetail(trade);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Success", result.getBody().getStatus());
        assertEquals("Stock Sell successfully", result.getBody().getMessage());
    }

    @Test
    public void testGetTradeDetail_Buy() {
        // Arrange
        Trade trade = new Trade(1, "stockId", 5, "Buy");
        Stock stock = new Stock("stockId", "StockName", 100.0, 105.0, 110.0, 90.0, 105.0);
        Portfolio portfolio = null;

        when(stockRepository.findByStockId("stockId")).thenReturn(stock);
        when(portfolioRepository.findByUserIdAndStockId(1, "stockId")).thenReturn(portfolio);

        ResponseEntity<ResponseStatus> result = tradeService.GetTradeDetail(trade);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Success", result.getBody().getStatus());
        assertEquals("Stock Buy successfully", result.getBody().getMessage());

    }
}
