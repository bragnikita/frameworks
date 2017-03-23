package org.bragnikita.framework.jee.domain.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;

/**
 * @author Brazhnikov Nikita
 */
@UsingDataSet
public class EntityArquillianTest extends Arquillian {
    @Deployment
    public static WebArchive createDeployment() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "org.bragnikita.framework.jee.domain")
                .addAsResource("")
                .addAsWebInfResource("test-ds.xml", "test-ds.xml")
                .addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"))
                .addAsWebInfResource(new File("src/main/resources/META-INF/beans.xml"))
                .addAsLibraries(libs);
    }

    @PersistenceContext(unitName = "MASTER.master")
    private EntityManager em;




}
