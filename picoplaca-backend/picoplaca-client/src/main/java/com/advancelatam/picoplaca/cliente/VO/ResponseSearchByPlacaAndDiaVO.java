package com.advancelatam.picoplaca.cliente.VO;

import com.advancelatam.picoplaca.cliente.models.Auto;
import com.advancelatam.picoplaca.cliente.models.Reglamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSearchByPlacaAndDiaVO {
    private List<Reglamento> reglametos;
    private Auto auto;
    private String messageError;
}
