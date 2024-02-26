package jwp.shower

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/sample")
class SampleApi {

    @PostMapping
    fun save(
        @RequestHeader("Authorization") token:String,
        @RequestBody sampleRequest: SampleRequest
    ): ResponseEntity<SampleResponse> {
        return ResponseEntity.ok(
            SampleResponse(
                name = sampleRequest.name,
                age = sampleRequest.age
            )
        )
    }
}