package com.advancelatam.picoplaca.ws.admin;

import com.advancelatam.picoplaca.cliente.models.Catalogo;
import com.advancelatam.picoplaca.cliente.services.ICatalogoService;
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
@RequestMapping("/api/catalogo/")
@CrossOrigin(origins= {"http://localhost:3000"})
public class CatalogoController {

    @Autowired
    @Lazy
    private ICatalogoService catalogoService;

    @GetMapping("/findByCodigo/{codigo}")
    public List<Catalogo> findAllByCodigo(@PathVariable String codigo) {
        return catalogoService.findAllByCodigo(codigo);
    }

    @PostMapping("/save/")
    public Catalogo save(@RequestBody Catalogo catalogo) {
        return catalogoService.save(catalogo);
    }
}
