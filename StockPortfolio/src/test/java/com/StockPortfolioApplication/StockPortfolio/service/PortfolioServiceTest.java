package com.StockPortfolioApplication.StockPortfolio.service;

import com.StockPortfolioApplication.StockPortfolio.DTO.PortfolioDTO;
import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Repository.PortfolioRepository;
import com.StockPortfolioApplication.StockPortfolio.Service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {
    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    void testGetAllPortfolio() {
        // Mock data
        int userId = 1;
        List<Portfolio> mockPortfolios = new ArrayList<>();
        mockPortfolios.add(new Portfolio(1, "AAPL", "Apple Inc.", 150.0, 160.0, 10, 1600.00, 0.00));
        mockPortfolios.add(new Portfolio(1, "GOOGL", "Alphabet Inc.", 1200.0, 1300.0, 8, 9600.0, 0.00));

        when(portfolioRepository.findAllByUserId(userId)).thenReturn(mockPortfolios);

        List<PortfolioDTO> result = portfolioService.getAllPortfolio(userId);

        verify(portfolioRepository, times(1)).findAllByUserId(userId);

        PortfolioDTO firstPortfolio = result.get(0);
        assertEquals("AAPL", firstPortfolio.getStockId());
        assertEquals("Apple Inc.", firstPortfolio.getStockName());
        assertEquals(150.0, firstPortfolio.getBuyPrice());
        assertEquals(160.0, firstPortfolio.getCurrentPrice());
        assertEquals(10, firstPortfolio.getTotalQuantity());

        PortfolioDTO secondPortfolio = result.get(1);
        assertEquals("GOOGL", secondPortfolio.getStockId());
        assertEquals("Alphabet Inc.", secondPortfolio.getStockName());
        assertEquals(1200.0, secondPortfolio.getBuyPrice());
        assertEquals(1300.0, secondPortfolio.getCurrentPrice());
        assertEquals(8, secondPortfolio.getTotalQuantity());

    }


}
