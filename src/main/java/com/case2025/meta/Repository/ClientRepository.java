package com.case2025.meta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.case2025.meta.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
