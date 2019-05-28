package com.advancelatam.picoplaca.ws.admin;

import com.advancelatam.picoplaca.cliente.VO.ResponseSearchByPlacaAndDiaVO;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionDataInvalid;
import com.advancelatam.picoplaca.cliente.models.Reglamento;
import com.advancelatam.picoplaca.cliente.services.IReglamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reglamento/")
@CrossOrigin(origins= {"http://localhost:3000"})
public class ReglamentoController {

    @Autowired
    @Lazy
    private IReglamentoService reglamentoService;

    @PostMapping("/save/")
    public Reglamento save(@RequestBody Reglamento reglamento)
    {
        return reglamentoService.save(reglamento);
    }

    @GetMapping("/findByPlacaAndDia/{placa}/{dia}")
    public ResponseSearchByPlacaAndDiaVO findByPlacaAndDia(@PathVariable String placa, @PathVariable String dia) {
        return reglamentoService.findByPlacaAndDia(placa, dia);
    }

    @GetMapping("/getAllReglamentosActives/")
    public List<Reglamento> getAllReglamentosActives() {
        return reglamentoService.findByEstado(Boolean.TRUE);
    }
}
