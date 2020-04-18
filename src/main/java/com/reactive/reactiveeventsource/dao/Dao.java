package com.reactive.reactiveeventsource.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Repository
public class Dao {
    private final JdbcTemplate jdbcTemplate;

    public Dao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Flux<Integer> getAllUniverseID()
    {
        return Flux.<Integer>create(objectFluxSink ->
                jdbcTemplate.query("SELECT universe_id FROM data_universe", rs -> {
                    while (rs.next())
                    {
                        objectFluxSink.next(rs.getInt(1));
                    }
                    objectFluxSink.complete();
                })).publishOn(Schedulers.elastic());
    }
}
