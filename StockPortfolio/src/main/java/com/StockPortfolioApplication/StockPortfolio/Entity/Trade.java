package com.StockPortfolioApplication.StockPortfolio.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TradeCompositeKey.class)
@Entity
public class Trade {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Id
    private String stockId;

    private int quantity;

    private String tradeType;

}
