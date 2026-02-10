package com.batyrhan.bankapi.repository.jdbc;

import com.batyrhan.bankapi.model.Customer;
import com.batyrhan.bankapi.patterns.builder.CustomerBuilder;
import com.batyrhan.bankapi.repository.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbc;

    public JdbcCustomerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int create(Customer c) {
        String sql = "INSERT INTO customers(name,email) VALUES (?,?)";
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            return ps;
        }, kh);
        Number id = kh.getKey();
        return id == null ? 0 : id.intValue();
    }

    @Override
    public Optional<Customer> findById(int id) {
        List<Customer> list = jdbc.query(
                "SELECT id,name,email FROM customers WHERE id=?",
                (rs, rowNum) -> new CustomerBuilder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build(),
                id
        );
        return list.stream().findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return jdbc.query(
                "SELECT id,name,email FROM customers ORDER BY id",
                (rs, rowNum) -> new CustomerBuilder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build()
        );
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM customers WHERE id=?", id);
    }
}