package com.batyrhan.bankapi.repository.jdbc;

import com.batyrhan.bankapi.model.AccountBase;
import com.batyrhan.bankapi.patterns.builder.AccountBuilder;
import com.batyrhan.bankapi.repository.AccountRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAccountRepository implements AccountRepository {

    private final JdbcTemplate jdbc;

    public JdbcAccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int create(AccountBase a) {
        String sql = "INSERT INTO accounts(account_number,balance,type,customer_id) VALUES (?,?,?,?)";
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, a.getAccountNumber());
            ps.setBigDecimal(2, a.getBalance());
            ps.setString(3, a.getAccountType());
            ps.setInt(4, a.getCustomerId());
            return ps;
        }, kh);
        Number id = kh.getKey();
        return id == null ? 0 : id.intValue();
    }

    private AccountBase map(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new AccountBuilder()
                .id(rs.getInt("id"))
                .accountNumber(rs.getString("account_number"))
                .balance(rs.getBigDecimal("balance"))
                .type(rs.getString("type"))
                .customerId(rs.getInt("customer_id"))
                .build();
    }

    @Override
    public Optional<AccountBase> findById(int id) {
        List<AccountBase> list = jdbc.query(
                "SELECT id,account_number,balance,type,customer_id FROM accounts WHERE id=?",
                (rs, rowNum) -> map(rs),
                id
        );
        return list.stream().findFirst();
    }

    @Override
    public List<AccountBase> findAll() {
        return jdbc.query(
                "SELECT id,account_number,balance,type,customer_id FROM accounts ORDER BY id",
                (rs, rowNum) -> map(rs)
        );
    }

    @Override
    public List<AccountBase> findByCustomerId(int customerId) {
        return jdbc.query(
                "SELECT id,account_number,balance,type,customer_id FROM accounts WHERE customer_id=? ORDER BY id",
                (rs, rowNum) -> map(rs),
                customerId
        );
    }

    @Override
    public void updateBalance(int id, BigDecimal balance) {
        jdbc.update("UPDATE accounts SET balance=? WHERE id=?", balance, id);
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM accounts WHERE id=?", id);
    }
}