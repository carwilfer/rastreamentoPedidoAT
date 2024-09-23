package com.infnet.pedido.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstoqueDTO {
    private Long id;
    private String produto;
    private Integer quantidade;
}
