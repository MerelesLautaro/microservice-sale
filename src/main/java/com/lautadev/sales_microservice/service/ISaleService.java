package com.lautadev.sales_microservice.service;

import com.lautadev.sales_microservice.dto.SaleDTO;
import com.lautadev.sales_microservice.model.Sale;

import java.util.List;

public interface ISaleService {
    public void saveSale(Sale sale);
    public List<SaleDTO> getSales();
    public SaleDTO findSale(Long id);
    public void deleteSale(Long id);
    public void editSale(Long id, Sale sale);
}
