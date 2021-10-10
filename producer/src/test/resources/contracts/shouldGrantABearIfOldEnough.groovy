package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
        Should grant a beer to a person who has at least 18 years old
        """)

    request {
        method POST()
        url "/check"
        body(
                age: 22, name: "igor"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""
            { "status" : "OK" }
        """)
        headers {
            contentType(applicationJson())
        }
    }
}
