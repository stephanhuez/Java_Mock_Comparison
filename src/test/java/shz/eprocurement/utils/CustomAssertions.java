package shz.eprocurement.utils;

import static org.junit.Assert.fail;

public final class CustomAssertions {

    public static final void then_AnExceptionShoulBeRaised() {
        fail("Should have raised an exception");
    }

}
