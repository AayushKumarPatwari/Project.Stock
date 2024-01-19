package com.StockPortfolioApplication.StockPortfolio;

import com.StockPortfolioApplication.StockPortfolio.Entity.Stock;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    private String status;
    private String message;
}
