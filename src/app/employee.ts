export interface Employee {
    
        "employeeId": number
        "currentPosition": string,
        "firstName": string,
        "lastName": string,
        "birthDate": string,
        "technologies": string,
        "client": {
            "clientId": number,
            "code": string,
            "name": string,
            "adress": null,
            "links": ''
        },
        "links": Array<
            {
                "rel": string,
                "href": string,
                "hreflang": string,
                "media": string,
                "title": string,
                "type": string,
                "deprecation": string
            }
        >
    }
