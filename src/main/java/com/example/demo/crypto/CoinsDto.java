package com.example.demo.crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CoinsDto {

    private String status;
    private Data data;

    @Getter
    @Setter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private Stats stats;
        private List<Coin> coins;
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        @ToString
        public static class Stats{
            private int total;
            private int totalCoins;
            private int totalMarkets;
            private int totalExchanges;
            private String totalMarketCap;
            private String total24hVolume;
        }
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        @ToString
        public static class Coin{
            private String uuid;
            private String symbol;
            private String name;
            private String price;
            private int rank;
            private int tier;
        }
    }
}
