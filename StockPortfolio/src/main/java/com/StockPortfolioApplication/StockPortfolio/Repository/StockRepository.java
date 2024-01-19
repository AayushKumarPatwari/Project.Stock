package com.StockPortfolioApplication.StockPortfolio.Repository;

import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByStockId(String stockId);
}
