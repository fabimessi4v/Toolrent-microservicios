package com.proyectogradle.msclientes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

    @Entity
    @Table(name = "customers")
    public class Customer {
        @Id
        @Column(name = "id", nullable = false, length = 36)
        private String id;

        @Column(name = "name", nullable = false, length = 100)
        private String name;

        @Column(name = "rut", nullable = false, length = 20, unique = true)
        private String rut;

        @Column(name = "phone", nullable = false, length = 20)
        private String phone;

        @Column(name = "email", nullable = false, length = 100)
        private String email;

        @Column(name = "status", nullable = false, length = 20)
        private String status;

        @Column(name = "created_at", updatable = false)
        private Instant createdAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRut() {
            return rut;
        }

        public void setRut(String rut) {
            this.rut = rut;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }
    }