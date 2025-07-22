package cholog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MemberController {
    @GetMapping("/hello")
    fun world(
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String,
        model: Model,
    ): String {
        model.addAttribute("name", name)
        return "greeting"
    }

    @GetMapping("/json")
    @ResponseBody
    fun json(): Person {
        return Person("brown", 20)
    }
}
