package com.advancelatam.picoplaca.ws.admin;

import com.advancelatam.picoplaca.cliente.VO.ResponseSaveAuto;
import com.advancelatam.picoplaca.cliente.models.Auto;
import com.advancelatam.picoplaca.cliente.services.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auto/")
@CrossOrigin(origins= {"http://localhost:3000"})
public class AutoController {

    @Autowired
    @Lazy
    private IAutoService autoService;

    @PostMapping("/save/")
    public ResponseSaveAuto save(@RequestBody Auto auto) {
        return autoService.save(auto);
    }

    @GetMapping("/findByPlaca/{placa}")
    public Optional<Auto> findByPlaca(@PathVariable String placa) {
        return autoService.findByPlaca(placa);
    }

}
