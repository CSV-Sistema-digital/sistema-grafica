
package br.com.grafica.backend.service;

import br.com.grafica.backend.dto.cliente.ClienteRequestDto;
import br.com.grafica.backend.dto.cliente.ClienteResponseDto;
import br.com.grafica.backend.model.Cliente;
import br.com.grafica.backend.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;


    @Transactional
    public ClienteResponseDto salvarPerfil(
            ClienteRequestDto request,
            UUID authUserId,
            String email) {

        Cliente cliente = clienteRepository
                .findByAuthUserId(authUserId)
                .orElseGet(Cliente::new);

        if (!request.cpf().equals(cliente.getCpf())
                && clienteRepository.existsByCpf(request.cpf())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "CPF ja cadastrado"
            );
        }

        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());
        cliente.setEmail(email);
        cliente.setTelefone(request.telefone());
        cliente.setAuthUserId(authUserId);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return converterParaDto(clienteSalvo);
    }


    @Transactional(readOnly = true)
    public ClienteResponseDto buscarClienteAutenticado(
            UUID authUserId) {

        Cliente cliente = clienteRepository
                .findByAuthUserId(authUserId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente nao encontrado"
                ));

        return converterParaDto(cliente);
    }

    private ClienteResponseDto converterParaDto(Cliente cliente) {

        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone()
        );
    }
}
