package constans;

import java.io.File;
import java.time.Duration;

public class Constant {
    public static class TimeoutVariables{
        public static final int IMPLICIT_WAIT = 5;
        public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(15);
    }

    public static class Urls {
        public static final String MAIN_MAIL_PAGE_URL = "https://mail.google.com/";
    }

    public static class TestData {
        public static final String TESTING_TEXT = "TestingText";
    }

    public static class Directory {
        public static final String OUTPUT_PATH = "src" + File.separator + "test" + File.separator + "artifacts" + File.separator + "output" + File.separator;
        public static final String INPUT_PATH = "src/test/artifacts/input/";
    }
}
