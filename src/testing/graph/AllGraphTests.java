package testing.graph;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testing.graph.basic.BasicDirectGraphTest;
import testing.graph.basic.BasicUndirectGraphTest;

@RunWith(Suite.class)
@SuiteClasses({ BasicDirectGraphTest.class, BasicUndirectGraphTest.class })
public class AllGraphTests {

}
