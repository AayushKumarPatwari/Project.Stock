package com.StockPortfolioApplication.StockPortfolio.Repository;

import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Entity.Trade;
import com.StockPortfolioApplication.StockPortfolio.Entity.TradeCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, TradeCompositeKey> {
}
