package eu.solidcraft.starter.domain.workshop

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, String> {
}
