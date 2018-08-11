package thomashan.github.io

import org.junit.jupiter.api.Test


class FirstStubClassTests {
    @Test
    void "the first test"() {
        FirstStubClass firstStubClass = new FirstStubClass()

        assert firstStubClass.returnInt() == 1
    }
}
