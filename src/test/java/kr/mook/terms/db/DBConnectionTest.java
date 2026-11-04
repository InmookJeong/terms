package kr.mook.terms.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest 
public class DBConnectionTest {

    @Autowired 
    private DataSource dataSource;

    @Test
    @DisplayName("MySQL 데이터베이스 연결 테스트")
    void testConnection() {
        try(Connection conn = dataSource.getConnection()) {
            assertThat(conn).isNotNull();
            assertThat(conn.getCatalog()).isEqualTo("terms"); // 실제 DB 이름으로 검증
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 실패: " + e.getMessage(), e);
        }
    }

}
