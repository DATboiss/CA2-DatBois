package test;

import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wtfak
 */
public class tester
{

    public static void main(String[] args)
    {
        Persistence.generateSchema("pu", new HashMap<>());
    }

}
