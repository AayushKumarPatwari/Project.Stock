package com.StockPortfolioApplication.StockPortfolio.Service;

import com.StockPortfolioApplication.StockPortfolio.DTO.PortfolioDTO;
import com.StockPortfolioApplication.StockPortfolio.DTO.StockDTO;
import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import com.StockPortfolioApplication.StockPortfolio.Repository.StockRepository;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public ResponseEntity<ResponseStatus> ParseAndUpdateStock(BufferedReader inputReader) throws IOException, ParseException {
        String line = null;
        boolean firstLine = true;
        List<Stock> inputs = new ArrayList<>();

        try{
            while ((line = inputReader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] columns = line.split(",");
                String stockID = columns[12];
                String stockName = columns[0];
                double openPrice = parseDouble(columns[2]);
                double closePrice = parseDouble(columns[5]);
                double highPrice = parseDouble(columns[3]);
                double lowPrice = parseDouble(columns[4]);
                double currentPrice = parseDouble(columns[7]);
                //to be updated
                Stock stock = new Stock(stockID, stockName, openPrice, closePrice, highPrice, lowPrice, currentPrice);
                inputs.add(stock);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatus("Failed", e.getMessage()));
        }
        try{
            stockRepository.saveAll(inputs);
            return ResponseEntity.ok(new ResponseStatus("Success","Stock File updated Successfully"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatus("Failed",e.getMessage()));
        }

    }


    public ResponseEntity<ResponseStatus> UpdateStock(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        return ParseAndUpdateStock(inputReader);
    }

    private StockDTO StockDtoToStock(Stock stock){
        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockId(stock.getStockId());
        stockDTO.setStockName(stock.getStockName());
        stockDTO.setOpenPrice(stock.getOpenPrice());
        stockDTO.setClosePrice(stock.getClosePrice());
        stockDTO.setLowPrice(stock.getLowPrice());
        stockDTO.setHighPrice(stock.getHighPrice());
        return stockDTO;
    }

    public Optional<StockDTO> findById(String stockId) {
        return stockRepository.findById(stockId).map(this::StockDtoToStock);

    }
}









