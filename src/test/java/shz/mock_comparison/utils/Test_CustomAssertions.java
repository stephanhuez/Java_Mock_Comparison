package shz.mock_comparison.utils;

import org.junit.Test;

public class Test_CustomAssertions {

    @Test(expected = AssertionError.class)
    public void shouldHaveRaisedAnException() {
        CustomAssertions.then_AnExceptionShoulBeRaised();
    }

}
