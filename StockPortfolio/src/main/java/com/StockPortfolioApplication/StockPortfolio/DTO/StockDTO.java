package com.StockPortfolioApplication.StockPortfolio.DTO;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class StockDTO {
    private String stockId;

    private String stockName;

    private Double openPrice;
    private Double closePrice;

    private Double lowPrice;
    private Double highPrice;
}
