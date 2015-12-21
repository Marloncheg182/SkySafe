package com.goodguys.skysafechat.spring.boot.configs.endpoints;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 * {@link AbstractEndpoint}
 * {@link Trace}
 * {@link TraceRepository}
 */

public class TraceEndpoint extends AbstractEndpoint<List<Trace>> {
    private final TraceRepository repository;

    public TraceEndpoint(TraceRepository repository) {
        super("websockettrace");
        Assert.notNull(repository, "Repository must not be null");
        this.repository = repository;
    }

    @Override
    public List<Trace> invoke() {
        return this.repository.findAll();
    }
}
