package com.nomura.unity.stp;

import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.api.JcrTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;
import java.io.File;


public class Warehousebootstrap {
    private final ModeShapeEngine modeShapeEngine;
    private final Logger slf4jLogger = LoggerFactory.getLogger(Warehousebootstrap.class);

    public Warehousebootstrap(ModeShapeEngine modeShapeEngine) throws Exception{
        slf4jLogger.warn("creating bootstrap");
        this.modeShapeEngine = modeShapeEngine;
        Repository repository = modeShapeEngine.getRepository("stpWarehouse");
        Session session = repository.login();

//        Node envs = session.getRootNode().addNode("environments");
//        envs.addNode("jscc", "whouse:ftpSite");
//        envs.addNode("lch","whouse:ftpSite");
//        session.save();

        Node root = session.getRootNode();
        NodeIterator nodes = root.getNodes("binarychild");
        while (nodes.hasNext()){
            Node thisNode = nodes.nextNode();
            System.out.println(thisNode.getDefinition().getName());
        }


    }


}
