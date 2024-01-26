package utn.frc.backend.curso3k2.TPIntegradorGrupo19.services;

import jakarta.persistence.Id;

import java.util.List;

public interface Service<T, W> {

    T getById(W id);
    List<T> getAll();
}
