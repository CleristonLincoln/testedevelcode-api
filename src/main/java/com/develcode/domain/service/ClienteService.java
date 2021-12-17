package com.develcode.domain.service;

import com.develcode.domain.exception.EntidadeNaoEncontradaException;
import com.develcode.domain.exception.FormatoImagemInvalidoException;
import com.develcode.domain.model.Cliente;
import com.develcode.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity<Cliente> salvarCliente(Cliente cliente) {

        Cliente clienteSalvo = repository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    public List<Cliente> buscarTodos() {

        return repository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Não foi possível localizar o cliente com o id %d", id)
                )
        );
    }

    public void salvarImagemCliente(Long id, MultipartFile file) throws IOException {

        String imagem = Objects.requireNonNull(file.getOriginalFilename());

        validarFormatoImagem(imagem);

        byte[] bytes = file.getBytes();
        Cliente cliente = buscarClientePorId(id);

        cliente.setFoto(bytes);
        repository.save(cliente);
    }

    private void validarFormatoImagem(String imagem) {
        String[] imagens = imagem.split("\\.");
        int i = imagens.length;
        String formatoImagem = imagens[i - 1];

        if(formatoImagem.isEmpty()){
            throw new FormatoImagemInvalidoException("É obrigatorio o envio de uma imagem");
        }

        if (!formatoImagem.equals("jpg")) {
            throw new FormatoImagemInvalidoException(
                    String.format("Formato de mídia inválido, está enviando um ." +
                            "%s e só aceita-se o formato .jpg", formatoImagem));
        }
    }

    public byte[] getImage(Long id) {
        Cliente cliente = buscarClientePorId(id);
        return cliente.getFoto();
    }
}
