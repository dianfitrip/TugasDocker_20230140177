package com.tugas.deploy6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mahasiswa {
    private String nama;
    private String nim;
    private String jenisKelamin;
}