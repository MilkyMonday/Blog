package com.ccz.web.admin;

import com.ccz.po.Blog;
import com.ccz.po.Type;
import com.ccz.service.BlogService;
import com.ccz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types")
    public String index() {
        return "redirect:/admin/types/list";
    }

    @GetMapping("/types/list")
    public String listTypes(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listTypes(pageable));
        return "admin/typeList";
    }

    @GetMapping("/types/save")
    public String saveType(@Valid Type type) {
        if (typeService.getTypeByName(type.getName()) != null) {
            return "admin/typeList";
        }
        typeService.saveType(type);
        return "redirect:/admin/types/list";
    }

    @GetMapping("/types/update")
    public String updateType(@Valid Type type) {
        if (typeService.getTypeByName(type.getName()) != null) {
            return "admin/typeList";
        }
        typeService.updateType(type);
        return "redirect:/admin/types/list";
    }

    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable Long id) {
        List<Blog> list = blogService.listBlogsByType(typeService.getType(id));
        blogService.deleteBlog(list);
        typeService.deleteType(id);
        return "redirect:/admin/types/list";
    }
}
