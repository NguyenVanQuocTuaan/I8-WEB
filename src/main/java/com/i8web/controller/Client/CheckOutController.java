package com.i8web.controller.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.i8web.Service.Client.ICheckOutService;
import com.i8web.Service.Client.IProductsService;
import com.i8web.Service.Client.IShoppingCartService;
import com.i8web.entity.Client.CartItem;
import com.i8web.entity.Client.CheckOut;
import com.i8web.entity.Client.Products;
import com.i8web.entity.Client.User;

@Controller
public class CheckOutController {
	
	@Autowired
	IProductsService productService;
	@Autowired
	IShoppingCartService cartService;
	@Autowired
	ICheckOutService checkService;
	
	User user = new User();
	CheckOut checkout = new CheckOut();
	
	@RequestMapping(value="/thanh-toan", method = RequestMethod.GET)
	public ModelAndView checkoutpage(HttpSession session) {
		session.setAttribute("CART",cartService.getAllItems());
		session.setAttribute("ITEMS",cartService.getCount());
		session.setAttribute("TOTAL",cartService.getTotal());
		ModelAndView mav = new ModelAndView ("checkout/checkout");
		return mav;
	}
	@GetMapping("/thanh-toan/add/{id}")
	   public String addCart(@PathVariable("id") Integer id) {
		Products product = productService.findProductById(id);
		CartItem item = new CartItem();
		if (product!=null){
			item.setId(product.getId());
			item.setImage(product.getImage());
			item.setName(product.getName());
			item.setPrice(product.getPrice_new());
			item.setQuantity(1);
			item.setAmount(item.getPrice(),item.getQuantity());
			cartService.add(item);
		}
		return "redirect:/thanh-toan";
	}
	
	@RequestMapping(value = "/thanh-toan/save", method = RequestMethod.POST)
	public String saveCart(@RequestParam("fullname") String name,@RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("phone") String phone, 
			@RequestParam("note") String note, @RequestParam("payment-method") String method) {
		if(checkService.checkvalid(name,email,address,phone,note,method)) {
			try {
				user.setName(name);
				user.setEmail(email);
				user.setAddress(address);
				user.setPhone(phone);
				user.setNote(note);
				checkout.setUser(user);
				checkout.setBill_num(cartService.getCount());
				checkout.setBill_total(cartService.getTotal());
				checkout.setDay(java.time.LocalDate.now());
				checkout.setStatus(method);
				checkout.setBill_detail(cartService.GetDetail());
				checkService.save(checkout);
				cartService.clear();
			} catch(Exception e)
			{
				System.out.print(e);
			}
		}else {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin!!","Error!!",JOptionPane.ERROR_MESSAGE);
		}		
		return "redirect:/thanh-toan";
	}
	
}
