package com.nomura.unity.stp;
import org.apache.commons.io.FileUtils;
import org.modeshape.jcr.ModeShapeEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.RepositoryFactory;


public class Warehousebootstrap {
    private final ModeShapeEngine modeShapeEngine;
    private final Logger slf4jLogger = LoggerFactory.getLogger(Warehousebootstrap.class);

    public Warehousebootstrap(ModeShapeEngine modeShapeEngine) {
        slf4jLogger.warn("creating bootstrap");
        this.modeShapeEngine = modeShapeEngine;
        System.out.println(modeShapeEngine.getRepositories());
    }


}
