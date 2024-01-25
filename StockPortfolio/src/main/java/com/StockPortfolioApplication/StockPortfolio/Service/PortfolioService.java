package com.StockPortfolioApplication.StockPortfolio.Service;

import com.StockPortfolioApplication.StockPortfolio.DTO.PortfolioDTO;
import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Repository.PortfolioRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    //@Cacheable(value="Portfolio",key="userId")
    public List<Portfolio> getPortfolio(int userId){
        List<Portfolio> portfolioResponse=portfolioRepository.findAllByUserId(userId);
        List<Portfolio>finalResponsePortfolio=new ArrayList<>();
        for (Portfolio portfolio: portfolioResponse) {
            if (portfolio.getTotalQuantity()>0) {
                finalResponsePortfolio.add(portfolio);
            }
        }
        return finalResponsePortfolio;
    }


    public List<PortfolioDTO> getAllPortfolio(int userId){
       List<Portfolio>finalResponsePortfolio = getPortfolio(userId);
       return PortDtoToPort(finalResponsePortfolio);

    }

    private List<PortfolioDTO> PortDtoToPort(List<Portfolio> portfolios){
        List<PortfolioDTO>portfolioDTOS=new ArrayList<>();
        for(Portfolio portfolio:portfolios)
        {
            PortfolioDTO portfolioDTO = new PortfolioDTO();
            portfolioDTO.setStockId(portfolio.getStockId());
            portfolioDTO.setStockName(portfolio.getStockName());
            portfolioDTO.setBuyPrice(portfolio.getBuyPrice());
            portfolioDTO.setCurrentPrice(portfolio.getCurrentPrice());
            portfolioDTO.setTotalQuantity(portfolio.getTotalQuantity());
            Double netPrice=portfolio.getTotalSellPrice()-portfolio.getTotalCostPrice();
            Double netPercent=(portfolio.getTotalSellPrice()-portfolio.getTotalCostPrice())/portfolio.getTotalCostPrice();

            portfolioDTO.setProfitAndLoss(netPrice);
            portfolioDTO.setProfitAndLossPercent(netPercent);
            portfolioDTOS.add(portfolioDTO);
        }

        return portfolioDTOS;
    }

}
