package com.lautadev.sales_microservice.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfSale;
    private CartDTO cartDTO;

}
