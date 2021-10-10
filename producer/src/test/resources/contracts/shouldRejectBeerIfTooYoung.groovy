package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
        Should reject person who has less than 18 years old
        """)

    request {
        method POST()
        url "/check"
        body(
                age: 17, name: "Łudasz"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""
            { "status" : "NOT_OK" }
        """)
        headers {
            contentType(applicationJson())
        }
    }
}
