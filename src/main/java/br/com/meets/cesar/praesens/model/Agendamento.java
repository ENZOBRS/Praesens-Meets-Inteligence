package br.com.meets.cesar.praesens.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {
    private Long id;
    private Paciente paciente;
    private LocalDateTime dataHora;
    private double scoreRisco;
}