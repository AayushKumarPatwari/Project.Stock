package com.StockPortfolioApplication.StockPortfolio.Entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PortfolioCompositeKey.class)

@Entity
public class Portfolio {
    @Id
    private int userId;

    @Id
    private String stockId;
    private String stockName;
    private Double buyPrice;
    private Double currentPrice;
    private int totalQuantity;
    private Double totalCostPrice;
    private Double totalSellPrice;
}
