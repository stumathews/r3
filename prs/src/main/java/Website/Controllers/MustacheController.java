/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Website.Controllers;

import Model.Book;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MustacheController {

    @RequestMapping(value = "/mustache")
    public ModelAndView test() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        List<Book> bookList=
                Arrays.asList(
                        new Book("Java ve Yaz?l?m Tasar?m?", 35d, "Altu? B. Alt?nta?", true),
                        new Book("Java Mimarisiyle Kurumsal Çözümler", 23d, "Rahman Usta", true),
                        new Book("Veri Yap?lar? ve Algoritmalar", 40d, "Rifat Çölkesen", false),
                        new Book("Veri Yap?lar? ve Algoritmalar", 40d, "Rifat Çölkesen", false),
                        new Book("Veri Yap?lar? ve Algoritmalar", 40d, "Rifat Çölkesen", true),
                        new Book("Mobil Pazarlama - SoLoMo", 15d, "Kahraman-Pelin Arslan", false),
                        new Book("Mobil Pazarlama - SoLoMo", 15d, "Kahraman-Pelin Arslan", true));

        modelAndView.addObject("bookList", bookList);

        return modelAndView;
    }
}
