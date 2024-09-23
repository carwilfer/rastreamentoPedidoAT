package com.infnet.logistica.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private String produto;
    private Integer quantidade;
    private String status;
}
