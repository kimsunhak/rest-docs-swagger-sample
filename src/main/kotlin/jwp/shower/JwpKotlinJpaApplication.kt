package jwp.shower

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwpKotlinJpaApplication

fun main(args: Array<String>) {
    runApplication<JwpKotlinJpaApplication>(*args)
}
