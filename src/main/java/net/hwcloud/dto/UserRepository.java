package net.hwcloud.dto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 사용자명으로 primary Key인 id를 조회하는 목적
    User findByUserId(String userId);
}
