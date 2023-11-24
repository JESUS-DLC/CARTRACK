package dev.jesusdlc.cartrack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestContainerTest extends AbstractTestContainers{
    @Test
    void canStartPostgresDB(){
        assertTrue(postgreSQLContainer.isRunning());
        assertTrue(postgreSQLContainer.isCreated());
    }


}
