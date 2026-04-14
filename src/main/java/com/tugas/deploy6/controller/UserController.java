package com.tugas.deploy6.controller;

import com.tugas.deploy6.model.Mahasiswa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140177"; // TODO: Ubah ke NIM aslimu

    // Data temporary (hilang saat server restart)
    private List<Mahasiswa> dataMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            session.setAttribute("user", username);
            return "redirect:/home";
        } else {
            // Pesan validasi keamanan spesifik
            model.addAttribute("error", "Sandi tidak valid. Pastikan panjang sandi sesuai dan kredensial sudah benar.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/";

        model.addAttribute("mahasiswas", dataMahasiswa);
        model.addAttribute("myNim", PASSWORD); // Untuk menampilkan NIM di header
        return "home";
    }

    @GetMapping("/form")
    public String formPage(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@RequestParam String nama,
                             @RequestParam String nim,
                             @RequestParam String jenisKelamin,
                             HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";

        dataMahasiswa.add(new Mahasiswa(nama, nim, jenisKelamin));
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}