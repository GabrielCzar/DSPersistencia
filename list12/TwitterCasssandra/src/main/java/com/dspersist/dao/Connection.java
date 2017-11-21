package com.dspersist.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Connection {
    protected Cluster cluster;
    protected Session session;

    public Connection(String node) {
        this.cluster = Cluster.builder().addContactPoint(node).build();
        this.session = cluster.connect();
    }

    public void close() {
        this.cluster.close();
        this.session.close();
    }
}
