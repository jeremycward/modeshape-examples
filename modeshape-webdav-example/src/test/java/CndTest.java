
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.modeshape.common.collection.Problems;
import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;
import org.modeshape.jcr.api.JcrTools;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

public class CndTest {
    @Test
    public void testLoadCndDefinitions() throws Exception{
        // Create and start the engine ...
        ModeShapeEngine engine = new ModeShapeEngine();
        engine.start();

        // Load the configuration for a repository via the classloader (can also use path to a file)...
        Repository repository = null;
        String repositoryName = null;


        URL url = CndTest.class.getClassLoader().getResource("config/in-memory-repository.json");
        assertThat(url, notNullValue());
        RepositoryConfiguration config = RepositoryConfiguration.read(url);


        // Verify the configuration for the repository ...
        Problems problems = config.validate();
        assertThat(problems.size(), is(0));

        // Deploy the repository ...
        repository = engine.deploy(config);
        repositoryName = config.getName();


        Session session = null;

        // Get the repository
        repository = engine.getRepository("testRepo");

        // Create a session ...
        session = repository.login("default");

        // Get the root node ...
        Node root = session.getRootNode();
        assert root != null;
        Node jsccNode =root.addNode("jscc", "whouse:ftpSite");
        Node lchNode =root.addNode("lch","whouse:ftpSite");
        Node importFolder = lchNode.addNode("intraday","whouse:importFolder");

        Node importedNode = importFolder.addNode("20160624","whouse:import");

        JcrTools tools = new JcrTools(true);
        File f = new File("g:/xx/xx.csv");
        assertThat(f.canRead(),is(true));
        tools.uploadFileAndBlock(session, f.toURI().toURL(),importedNode.getPath());

    }

    public void createRepo() throws Exception {

    }


}
