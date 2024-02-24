package jwp.shower

import com.epages.restdocs.apispec.*
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.fasterxml.jackson.databind.ObjectMapper
import jwp.shower.library.RestDocsUtils
import jwp.shower.library.RestDocsUtils.Companion.requestPreprocessor
import jwp.shower.library.RestDocsUtils.Companion.responsePreprocessor
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringRestDocsTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
class SampleApiDocs(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {
    @Test
    @DisplayName("저장")
    fun save() {
        // given
        val sampleRequest = SampleRequest(
            "김선학",
            26
        )

        val json = objectMapper.writeValueAsString(sampleRequest)

        mockMvc.perform(
            RestDocumentationRequestBuilders.post("/v1/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isOk())
            .andDo(
                MockMvcRestDocumentationWrapper.document(
                    "sample-save",
                    requestPreprocessor(),
                    responsePreprocessor(),
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("sample-save")
                            .requestFields(
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("age").description("나이"),
                            )
                            .requestSchema(Schema.schema("SampleRequest"))
                            .responseFields(
                                FieldDescriptors().and(
                                    fieldWithPath("name").description("이름"),
                                    fieldWithPath("age").description("나이")
                                )
                            )
                            .responseSchema(Schema.schema("SampleResponse"))
                            .build()
                    )
                )
            )
    }

}