package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.model.Paciente;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private PacienteRepository repository;

    public String realizarAgendamento(String cpf, String nome) {
        // O JPA gerencia a busca e atualização de forma segura
        Paciente paciente = repository.findByCpf(cpf)
                .orElse(new Paciente(null, cpf, nome, 0, 0));

        paciente.setTotalAgendamentos(paciente.getTotalAgendamentos() + 1);
        repository.save(paciente); // Salva ou Atualiza automaticamente

        return "Agendado! Risco: " + String.format("%.2f", paciente.getScoreRisco()) + "%";
    }

    public String registrarFalta(String cpf) {
        return repository.findByCpf(cpf).map(paciente -> {
            paciente.setTotalFaltas(paciente.getTotalFaltas() + 1);
            repository.save(paciente);
            return "Falta registrada para " + paciente.getNome() + ". Novo risco: " 
                    + String.format("%.2f", paciente.getScoreRisco()) + "%";
        }).orElse("Paciente não encontrado.");
    }

    public List<Paciente> obterListaPorRisco() {
        return repository.findAll(); // O banco retorna a lista atualizada
    }
}