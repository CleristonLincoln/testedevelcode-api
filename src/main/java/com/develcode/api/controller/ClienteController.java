package com.develcode.api.controller;

import com.develcode.domain.model.Cliente;
import com.develcode.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> buscarClientes() {
        return service.buscarTodos();
    }

    /**
     * Salva um novo {@link Cliente}, a imagem deverá ser enviada por outro endipoint passando o id do retorno deste
     * método + a imagem
     * @param cliente {@link Cliente}
     *
     * @return cliente criado no banco de dados
     */
    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@Valid @RequestBody Cliente cliente) {
        return service.salvarCliente(cliente);

    }

    /**
     * @param id Código do cliente para salvar a imagem
     * @param file imagem no formado ".jpg"
     * @throws IOException
     */
    @PostMapping("/file/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void salvarImagemCliente(@RequestParam("id") Long id, @RequestParam MultipartFile file) throws IOException {
        service.salvarImagemCliente(id, file);
    }


    @GetMapping(value = "/file/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] image = service.getImage(id);
        InputStream in = new ByteArrayInputStream(image);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "\"")
                .body(image);

    }

}
