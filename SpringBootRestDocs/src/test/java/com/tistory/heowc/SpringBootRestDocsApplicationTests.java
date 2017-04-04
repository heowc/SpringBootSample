package com.tistory.heowc;

import com.tistory.heowc.domain.Programming;
import com.tistory.heowc.repository.ProgrammingRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestDocsApplicationTests {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("static/docs");

	private RestDocumentationResultHandler documentationHandler;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ProgrammingRepository repository;

	@Before
	public void setUp() {
		this.documentationHandler = document("{method-name}",
										preprocessRequest(prettyPrint()),
										preprocessResponse(prettyPrint()));

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
						.apply(documentationConfiguration(this.restDocumentation))
						.alwaysDo(this.documentationHandler)
						.build();

		repository.save(new Programming("java", "oracle.com"));
	}

	@Test
	public void test_findAll() throws Exception {

	}
}
