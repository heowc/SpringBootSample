package com.example.kotlin.onetoone.repository

import com.example.kotlin.onetoone.domain.Owner
import org.springframework.data.jpa.repository.JpaRepository

interface OwnerRepository : JpaRepository<Owner, Long>
