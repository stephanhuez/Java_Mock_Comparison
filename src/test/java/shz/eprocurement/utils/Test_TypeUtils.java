package shz.eprocurement.utils;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test_TypeUtils {

    @Test
    public void shouldContainThreeArguments() {
        ArrayList<String> arguments = TypeUtils.buildArguments(new String[] { "1", "2", "3" });
        assertThat(arguments.contains("1"), is(true));
    }

}
