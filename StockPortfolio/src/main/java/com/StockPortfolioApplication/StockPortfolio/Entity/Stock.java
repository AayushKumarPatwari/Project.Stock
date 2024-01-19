package com.StockPortfolioApplication.StockPortfolio.Entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    @Id
    private String stockId;

    private String stockName;

    private Double openPrice;
    private Double closePrice;

    private Double lowPrice;
    private Double highPrice;
    private Double currentPrice;
}
