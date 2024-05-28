package com.lautadev.sales_microservice.service;

import com.lautadev.sales_microservice.dto.CartDTO;
import com.lautadev.sales_microservice.dto.SaleDTO;
import com.lautadev.sales_microservice.model.Sale;
import com.lautadev.sales_microservice.repository.ICartAPIClient;
import com.lautadev.sales_microservice.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService {
    @Autowired
    private ISaleRepository saleRepo;

    @Autowired
    private ICartAPIClient cartAPI;

    @Override
    public void saveSale(Sale sale) {
        saleRepo.save(sale);
    }

    @Override
    public List<SaleDTO> getSales() {
        List<Sale> listSale = saleRepo.findAll();
        List<SaleDTO> listSaleDTO = new LinkedList<>();

        // Recolectar todos los IDs de carts de todas las ventas
        List<Long> allIdCarts = new ArrayList<>();
        for (Sale sale : listSale) {
            allIdCarts.add(sale.getIdCart());
        }

        // Llamar a la API de carts una vez para obtener todos los carts necesarios
        List<CartDTO> allCarts = cartAPI.listCartsById(allIdCarts);

        // Mapear los carts obtenidos por su ID para un acceso rápido
        Map<Long, CartDTO> cartsMap = allCarts.stream()
                .collect(Collectors.toMap(CartDTO::getId, cart -> cart));

        // Construir SaleDTO para cada Sale
        for (Sale sale : listSale) {
            CartDTO cartDTO = cartsMap.get(sale.getIdCart());
            listSaleDTO.add(new SaleDTO(sale.getId(), sale.getDateOfSale(), cartDTO));
        }

        return listSaleDTO;
    }

    @Override
    public SaleDTO findSale(Long id) {
        Sale sale = saleRepo.findById(id).orElse(null);
        assert sale != null;
        CartDTO cartDTO = cartAPI.findCart(sale.getIdCart());
        return new SaleDTO(sale.getId(),sale.getDateOfSale(),cartDTO);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepo.deleteById(id);
    }

    @Override
    public void editSale(Long id, Sale sale) {

    }
}
