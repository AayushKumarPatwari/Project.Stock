package com.StockPortfolioApplication.StockPortfolio.DTO;

import lombok.Data;

@Data
public class PortfolioDTO {
    private String stockId;
    private String stockName;
    private Double buyPrice;
    private Double currentPrice;
    private int totalQuantity;
    private Double ProfitAndLoss;
    private Double ProfitAndLossPercent;
}
