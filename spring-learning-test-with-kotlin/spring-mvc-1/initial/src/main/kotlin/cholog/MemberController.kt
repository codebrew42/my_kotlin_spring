package cholog

import org.springframework.stereotype.Controller

@Controller
class MemberController {
    fun world(): String {
        // TODO: Configure the application so that when a request is made to /hello, the resources/templates/greeting.html page responds.
        // TODO: Make sure that when a 'name' query parameter is received in a request, its value can be used in greeting.html.
        return ""
    }

    fun json(): Person {
        // TODO: Configure the application to respond with the data {"name": "brown", "age": 20} when a request is made to /json.
        return Person("", 0)
    }
}
