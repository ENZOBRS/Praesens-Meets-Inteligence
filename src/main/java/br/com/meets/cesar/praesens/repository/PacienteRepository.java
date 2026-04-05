package br.com.meets.cesar.praesens.repository;

import br.com.meets.cesar.praesens.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpf(String cpf);
    
    // Busca todos ordenados pelo risco (calculado ou via histórico)
    List<Paciente> findAllByOrderByTotalFaltasDesc();
}