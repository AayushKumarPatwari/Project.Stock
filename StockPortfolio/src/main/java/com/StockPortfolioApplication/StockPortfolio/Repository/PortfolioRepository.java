package com.StockPortfolioApplication.StockPortfolio.Repository;

import com.StockPortfolioApplication.StockPortfolio.Entity.Portfolio;
import com.StockPortfolioApplication.StockPortfolio.Entity.PortfolioCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, PortfolioCompositeKey> {

    List<Portfolio> findAllByUserId(int userId);
    Portfolio findByUserId(int userId);

    Portfolio findByUserIdAndStockId(int userId, String stockId);
}
