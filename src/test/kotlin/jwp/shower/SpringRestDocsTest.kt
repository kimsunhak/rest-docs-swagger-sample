package jwp.shower

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.context.ContextConfiguration

@Tag("restdocs")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = [SpringRestDocsTestConfig::class])
@ExtendWith(RestDocumentationExtension::class)
annotation class SpringRestDocsTest

@ComponentScan
@EnableAutoConfiguration
class SpringRestDocsTestConfig {}