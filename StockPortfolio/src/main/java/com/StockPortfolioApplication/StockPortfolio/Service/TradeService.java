package com.StockPortfolioApplication.StockPortfolio.Service;

import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import com.StockPortfolioApplication.StockPortfolio.Entity.Trade;
import com.StockPortfolioApplication.StockPortfolio.Entity.TradeCompositeKey;
import com.StockPortfolioApplication.StockPortfolio.Repository.PortfolioRepository;
import com.StockPortfolioApplication.StockPortfolio.Repository.StockRepository;
import com.StockPortfolioApplication.StockPortfolio.Repository.TradeRepository;
import com.StockPortfolioApplication.StockPortfolio.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.Optional;

@Service
@Slf4j
public class TradeService {
    @Autowired PortfolioRepository portfolioRepository;
    @Autowired TradeRepository tradeRepository;
    @Autowired StockRepository stockRepository;
    public ResponseEntity<ResponseStatus> GetTradeDetail(Trade trade) {

       // log.info("Trade Quantity: {}",trade.getQuantity());
        Trade newTrade = new Trade(trade.getUserId(), trade.getStockId(), trade.getQuantity(), trade.getTradeType());
        if(trade.getQuantity()<=0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseStatus("Failed","Enter Valid Quantity"));
        }
        if(!trade.getTradeType().equals("Sell") && !trade.getTradeType().equals("Buy")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseStatus("Failed", "TradeType is wrong"));
        }
        String stockId = trade.getStockId();
        if(stockId==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseStatus("Failed", "Stock ID cannot be empty!"));
        }

        try{
            Stock stock= stockRepository.findByStockId(stockId);
            if(stock==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatus("Failed", "Stock ID is not valid"));
            }
            Portfolio portfolio = portfolioRepository.findByUserIdAndStockId(trade.getUserId(),trade.getStockId());

            //check if TrdaeType is sell
            if(trade.getTradeType().equals("Sell")){
                //if stock is already purchased
                if(portfolio==null){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseStatus("Failed","Stock can not be sell"));
                }
                if(portfolio.getTotalQuantity()< trade.getQuantity()){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseStatus("Failed","Stock quantity not sufficient for sell"));
                }
                try{
                    int remainQuantity = portfolio.getTotalQuantity()- trade.getQuantity();
                   // log.info("Portfolio Total Quantity: {}",portfolio.getTotalQuantity());
                    portfolio.setTotalQuantity(remainQuantity);
                    double totalSellPrice = portfolio.getTotalSellPrice()+(stock.getCurrentPrice()* trade.getQuantity());
                    portfolio.setTotalSellPrice(totalSellPrice);
                   // log.info("Portfolio Total Quantity: {}",portfolio.getTotalQuantity());
                    tradeRepository.save(newTrade);
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatus("Success","Stock "+ trade.getTradeType()+" successfully"));
                }
                catch (Exception e){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseStatus("Selling Stock Failed",e.getMessage()));
                }
            }else{
//                TradeCompositeKey tradeCompositeKey= new TradeCompositeKey(trade.getUserId(),trade.getStockId());
//                Optional<Trade> existrade=tradeRepository.findById(tradeCompositeKey);
                if(portfolio!=null){
                    try{
                        int remainQuantity = portfolio.getTotalQuantity()+trade.getQuantity();
                        //log.info("Portfolio Total Quantity: {}",portfolio.getTotalQuantity());
                        portfolio.setTotalQuantity(remainQuantity);
                        double totalCostPrice = portfolio.getTotalCostPrice()+(stock.getCurrentPrice()*trade.getQuantity());
                        portfolio.setTotalCostPrice(totalCostPrice);
                        tradeRepository.save(newTrade);
                       // log.info("Portfolio Total Quantity: {}",portfolio.getTotalQuantity());
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatus("Success","Stock "+ trade.getTradeType()+" successfully"));
                    }
                    catch (Exception e){
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseStatus("Buying Stock Failed",e.getMessage()));
                    }
                }else{
                    try{
                        tradeRepository.save(newTrade);

                        double totalCost= trade.getQuantity()*stock.getCurrentPrice();
                        Portfolio newportfolio= new Portfolio(trade.getUserId(), trade.getStockId(), stock.getStockName(), stock.getOpenPrice(), stock.getCurrentPrice(), trade.getQuantity(), totalCost, 0.0);
                        portfolioRepository.save(newportfolio);
                        //log.info("Portfolio Total Quantity: {}",newportfolio.getTotalQuantity());
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatus("Success","Stock "+ trade.getTradeType()+" successfully"));
                    }
                    catch (Exception e){
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseStatus("Buying Stock Failed",e.getMessage()));
                    }

                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseStatus("Failed",e.getMessage()));

        }

    }
}





