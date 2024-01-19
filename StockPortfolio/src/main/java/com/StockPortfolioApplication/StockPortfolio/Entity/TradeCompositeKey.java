package com.StockPortfolioApplication.StockPortfolio.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TradeCompositeKey implements Serializable {
    private int userId;
    private String stockId;
}
