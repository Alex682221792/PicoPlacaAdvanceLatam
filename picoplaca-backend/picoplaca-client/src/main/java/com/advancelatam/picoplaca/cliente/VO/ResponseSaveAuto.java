package com.advancelatam.picoplaca.cliente.VO;

import com.advancelatam.picoplaca.cliente.models.Auto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSaveAuto {
    private Auto auto;
    private String mensajeError;
}
