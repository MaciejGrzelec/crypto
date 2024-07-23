package com.example.demo.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CryptoService {

    private final WebClient webClient;
    private final CoinRepository coinRepository;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    public CryptoService(WebClient.Builder webClientBuilder, CoinRepository coinRepository) {
        this.webClient = webClientBuilder.baseUrl("https://coinranking1.p.rapidapi.com").build();
        this.coinRepository = coinRepository;
    }

    public void getCoins(@RequestParam String time, @RequestParam String orderBy, Pageable pageable) {
        String endpoint;

        if (pageable != null) {
            endpoint = String.format("/coins?timePeriod=%s&orderBy=%s&limit=%d&offset=%d",
                    time, orderBy, pageable.getPageSize(), pageable.getOffset());
        } else {
            endpoint = String.format("/coins?timePeriod=%s&orderBy=%s", time, orderBy);
        }

        Mono<String> responseMono = this.webClient.get()
                .uri(endpoint)
                .header("x-rapidapi-host", "coinranking1.p.rapidapi.com")
                .header("x-rapidapi-key", rapidApiKey)
                .retrieve()
                .bodyToMono(String.class);

        String responseBody = responseMono.block();
        System.out.println("Response JSON: " + responseBody);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CoinsDto response = objectMapper.readValue(responseBody, CoinsDto.class);
            List<CoinsDto.Data.Coin> coins = response.getData().getCoins();
            List<CoinEntity> entityList = coins.stream().map(e -> {
                System.out.println(e);
                return CoinEntity.fromDto(e);
            }).toList();
            coinRepository.saveAll(entityList);
        } catch (Exception e) {
            System.err.println("Error parsing JSON to response: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
