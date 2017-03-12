package test.by.sardyka.tour;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses( { DataReaderTest.class, DataParserTest.class, RequestHandlerTest.class,
	RequestParserTest.class, TourCreatorTest.class, DataWriterTest.class } )
@RunWith(Suite.class)
public class TourSuite {
}
