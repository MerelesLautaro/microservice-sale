package com.lautadev.sales_microservice.repository;

import com.lautadev.sales_microservice.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="cart-microservice")
public interface ICartAPIClient {

    @GetMapping("/cart/get/{id}")
    public CartDTO findCart(@PathVariable ("id") Long id);

    @GetMapping("/cart/listCartsById")
    public List<CartDTO> listCartsById(@RequestParam List<Long> idCarts);
}
