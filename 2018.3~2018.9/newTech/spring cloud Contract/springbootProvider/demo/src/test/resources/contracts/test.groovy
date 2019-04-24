package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url('/hello') {
            queryParameters {
                parameter("name", "zhangsan")
            }
        }

    }
    response {
        status 200
        body("123456")
        headers {
            header('Content-Type': 'text/plain;charset=ISO-8859-1')
        }
    }
}
