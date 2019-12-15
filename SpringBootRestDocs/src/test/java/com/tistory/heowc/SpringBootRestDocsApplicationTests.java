package com.tistory.heowc;

import com.tistory.heowc.web.ProgrammingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProgrammingController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
class SpringBootRestDocsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/programming"))
               .andExpect(status().isOk())
               .andDo(document("programming"));
    }
}
