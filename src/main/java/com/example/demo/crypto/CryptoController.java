package com.example.demo.crypto;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {
    //https://rapidapi.com/Coinranking/api/coinranking1/playground/apiendpoint_bee30194-d61d-40a5-ae45-fb69ba9f1af0
    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("coins")
    public void getCoins(@RequestParam String time, @RequestParam String orderBy, Pageable pageable) {
        cryptoService.getCoins(time, orderBy, pageable);
    }
}
