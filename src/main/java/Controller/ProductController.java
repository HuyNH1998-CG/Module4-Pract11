package Controller;

import Model.Product;
import Model.ProductForm;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    private final ProductService service = new ProductService();

    @GetMapping("")
    public ModelAndView index(){
        List<Product> products = service.finAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("products",products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(@ModelAttribute("productForm")ProductForm form){
        return new ModelAndView("/create");
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute("productForm") ProductForm form){
        MultipartFile multipartFile = form.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(form.getImage().getBytes(),new File(fileUpload,fileName));
        } catch (Exception e){
            e.printStackTrace();
        }
        Product product = new Product(form.getId(),form.getName(),form.getDescription(),fileName);
        service.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }
}
