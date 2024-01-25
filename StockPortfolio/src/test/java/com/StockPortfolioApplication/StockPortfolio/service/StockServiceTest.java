package com.StockPortfolioApplication.StockPortfolio.service;
import com.StockPortfolioApplication.StockPortfolio.Service.StockService;
import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import com.StockPortfolioApplication.StockPortfolio.Repository.StockRepository;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    public void testUpdateStock() throws IOException, ParseException {
        // Arrange
        String fileContent = "SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN\n" +
                "1018GS2026,GS,115.54,115.54,115.54,115.54,115.54,121.62,1,115.54,12-JAN-2024,1,IN0020010081\n" +
                "182D020524,TB,97.76,97.76,97.76,97.76,97.76,97.75,500,48880,12-JAN-2024,4,IN002023Y326";

        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", fileContent.getBytes());

        List<Stock> savedStocks = new ArrayList<>();
        savedStocks.add(new Stock("ABC123", "ABC", 100.0, 105.0, 110.0, 90.0, 105.0));
        savedStocks.add(new Stock("XYZ456", "XYZ", 50.0, 55.0, 60.0, 45.0, 55.0));

        when(stockRepository.saveAll(any())).thenReturn(savedStocks);

        // Act
        ResponseEntity<ResponseStatus> result = stockService.UpdateStock(file);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Success", result.getBody().getStatus());
        assertEquals("Stock File updated Successfully", result.getBody().getMessage());


        Mockito.verify(stockRepository, Mockito.times(1)).saveAll(any());

    }

}
