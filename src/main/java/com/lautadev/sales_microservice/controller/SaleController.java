package com.lautadev.sales_microservice.controller;

import com.lautadev.sales_microservice.dto.SaleDTO;
import com.lautadev.sales_microservice.model.Sale;
import com.lautadev.sales_microservice.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private ISaleService saleServ;

    @PostMapping("/save")
    public String saveSale (@RequestBody Sale sale){
        saleServ.saveSale(sale);
        return "Sale saved Successfully";
    }

    @GetMapping("/get")
    public List<SaleDTO> getSales(){
        return saleServ.getSales();
    }

    @GetMapping("/get/{id}")
    public SaleDTO findSale(@PathVariable Long id){
        return saleServ.findSale(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id){
        saleServ.deleteSale(id);
        return "Sale deleted";
    }
}
