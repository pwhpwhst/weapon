package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/producers/message'
        body(
            "message":"Hello World"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""{
            "id": "${value("1000")}",
            "content": "${value("Hello Fine!")}"
       }""")
        headers {
            contentType(applicationJson())
        }
    }
}