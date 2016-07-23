package com.giga.warehouse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.modeshape.jcr.JcrRepository;
import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;

import javax.jcr.Repository;
import javax.jcr.Session;

public class TestCnd {

    @Test
    public void testCnd()throws Exception{
        URL url = getClass().getResource("/my-repository-config.json");
        assertNotNull(url);
        ModeShapeEngine engine = new ModeShapeEngine();
        engine.start();
        RepositoryConfiguration repositoryConfiguration = RepositoryConfiguration.read(url);
        Repository repo =engine.deploy(repositoryConfiguration);
        Session sess = repo.login();
        sess.getRootNode();
    }

}
