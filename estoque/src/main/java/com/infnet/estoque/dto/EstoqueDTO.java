package com.infnet.estoque.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
    private Long id;
    private String produto;
    private Integer quantidade;
}
